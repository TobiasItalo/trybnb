package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.BookingById

class BookingByIdAdapter(private val bookingById: List<BookingById>) :
    Adapter<BookingByIdAdapter.BookingByIdViewHolder>() {
    class BookingByIdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.name_item_reservation)
        val lastName: TextView = view.findViewById(R.id.name_item_reservation)
        val totalPrice: TextView = view.findViewById(R.id.total_price_item_reservation)
        val checkIn: TextView = view.findViewById(R.id.checkin_item_reservation)
        val checkOut: TextView = view.findViewById(R.id.checkout_item_reservation)
        val additionalNeeds: TextView = view.findViewById(R.id.additional_needs_item_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingByIdViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservation, parent, false)
        return BookingByIdViewHolder(view)
    }

    override fun getItemCount(): Int = bookingById.size

    override fun onBindViewHolder(holder: BookingByIdViewHolder, position: Int) {
        holder.firstName.text = bookingById[position].firstName
        holder.lastName.text = bookingById[position].lastName
        holder.totalPrice.text = bookingById[position].totalPrice.toString()
        holder.checkIn.text = bookingById[position].bookingDates.checkIn
        holder.checkOut.text = bookingById[position].bookingDates.checkOut
        holder.additionalNeeds.text = bookingById[position].additionalNeeds
    }
}
