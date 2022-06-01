package com.example.psychodoctor.ui.home


import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychodoctor.data.local.ClientPreferences
import com.example.psychodoctor.databinding.FragmentHomeBinding
import com.example.psychodoctor.model.appointmentResponse.Reservation
import com.example.psychodoctor.ui.home.adapter.AppointmentsAdapter
import com.example.psychodoctor.util.base.BaseFragment
import com.example.psychodoctor.util.extension.OnItemClickListener
import com.example.psychodoctor.util.extension.loadImageView
import com.example.psychodoctor.util.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()
    private val args by navArgs<HomeFragmentArgs>()

    private var appointmentList: MutableList<Reservation> = mutableListOf()
    private var temporaryList: MutableList<Reservation> = mutableListOf()


    override fun onCreateFinished() {
        binding.ivDoctorImage.loadImageView(args.authResponse.photoUrl.toString())
        binding.tvDoctorName.text = args.authResponse.name.toString()
        binding.tvProfession.text = args.authResponse.profession.toString()
        binding.tvWorkplace.text = args.authResponse.workplace.toString()

        viewModel.getAppointments(ClientPreferences(requireContext()).getDocId().toString())
        setRecyclerViewAdapter()
    }

    override fun initListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            refresh()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            //Appointment Details
            appointmentResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    appointmentList.clear()
                    it.reservation?.forEach { appointment ->
                        if(appointment.approval.equals("awaiting approval")){
                            appointmentList.add(appointment)
                        }
                    }
                }
                temporaryList.clear()
                temporaryList.addAll(appointmentList)
                (binding.rvAppointments.adapter as AppointmentsAdapter).updateList(temporaryList)
            })

            appointmentResponseIsLoading.observe(viewLifecycleOwner, Observer {
                appointmentActions(it)
            })

            appointmentResponseError.observe(viewLifecycleOwner, Observer {
                snack(requireView(), it.toString())
            })
        }

    }

    private fun setRecyclerViewAdapter() {
        val mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvAppointments.layoutManager = mLayoutManager
        val mAdapter = AppointmentsAdapter(requireContext(),object : OnItemClickListener{
            override fun onClick(reservation: Reservation) {
                val action = HomeFragmentDirections.actionHomeFragmentToAppointmentDetailFragment(reservation)
                findNavController().navigate(action)
            }
        })
        binding.rvAppointments.adapter = mAdapter
    }


    private fun appointmentActions(isLoading: Boolean = false) {
        binding.pbAppointmentList.isVisible = isLoading
    }

    private fun refresh(){
        binding.ivDoctorImage.loadImageView(args.authResponse.photoUrl.toString())
        binding.tvDoctorName.text = args.authResponse.name.toString()
        binding.tvProfession.text = args.authResponse.profession.toString()
        binding.tvWorkplace.text = args.authResponse.workplace.toString()

        viewModel.getAppointments(ClientPreferences(requireContext()).getDocId().toString())

        binding.swipeToRefresh.isRefreshing = false
    }
}