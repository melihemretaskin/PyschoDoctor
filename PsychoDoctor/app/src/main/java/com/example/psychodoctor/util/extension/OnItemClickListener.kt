package com.example.psychodoctor.util.extension

import com.example.psychodoctor.model.appointmentResponse.Reservation


interface OnItemClickListener {
    fun onClick(reservation: Reservation)
}