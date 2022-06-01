package com.example.psychodoctor.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.psychodoctor.R
import com.example.psychodoctor.databinding.FragmentAppointmentDetailBinding
import com.example.psychodoctor.ui.auth.login.LoginFragmentDirections
import com.example.psychodoctor.util.base.BaseFragment
import com.example.psychodoctor.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentDetailFragment : BaseFragment<FragmentAppointmentDetailBinding,AppointmentDetailViewModel>(
    FragmentAppointmentDetailBinding::inflate
) {
    override val viewModel by viewModels<AppointmentDetailViewModel>()
    private val args by navArgs<AppointmentDetailFragmentArgs>()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.tvAppointmentDate.text = args.appointmentDetail.reservationDate.toString()
        binding.tvAppointmentState.text = args.appointmentDetail.approval.toString()
        binding.tvPatientEmail.text = args.appointmentDetail.email.toString()

        binding.btnAccept.setOnClickListener {
            viewModel.confirmAppointment(args.appointmentDetail.resId.toString(),"")
        }
        binding.btnDecline.setOnClickListener {
            viewModel.declineAppointment(args.appointmentDetail.resId.toString(),"")
        }

    }

    override fun observeEvents() {
        with(viewModel){
            result.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }
}