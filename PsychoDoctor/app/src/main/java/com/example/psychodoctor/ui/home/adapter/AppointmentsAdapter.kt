package com.example.psychodoctor.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.psychodoctor.R
import com.example.psychodoctor.databinding.AppointmentItemRowBinding
import com.example.psychodoctor.model.appointmentResponse.Reservation
import com.example.psychodoctor.util.extension.OnItemClickListener


class AppointmentsAdapter(
    private val context : Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>() {

    private var appointmentList = mutableListOf<Reservation>()

    class ViewHolder(private val itemBinding : AppointmentItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(reservation: Reservation, context: Context,listener: OnItemClickListener) {
            itemBinding.appointmentResponse = reservation
            itemBinding.onItemClickListener = listener
            itemBinding.executePendingBindings()

            if(reservation.approval == "awaiting approval"){
                itemBinding.tvAppointmentState.setTextColor(ContextCompat.getColor(context,R.color.approval_awaiting))
            }
            else if(reservation.approval == "denied"){
                itemBinding.tvAppointmentState.setTextColor(ContextCompat.getColor(context,R.color.approval_declined))
            }
            else if(reservation.approval == "approved"){
                itemBinding.tvAppointmentState.setTextColor(ContextCompat.getColor(context, R.color.approval_accepted))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AppointmentItemRowBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(appointmentList[position],context,onItemClickListener)
    }

    override fun getItemCount(): Int = appointmentList.size

    fun updateList(_appointmentList : MutableList<Reservation>){
        appointmentList = _appointmentList
        notifyDataSetChanged()
    }
}