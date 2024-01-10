package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookerApiClient
import com.betrybe.trybnb.data.models.BookingById
import com.betrybe.trybnb.ui.adapters.BookingByIdAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ReservationFragment : Fragment() {
    private lateinit var reservationRecyclerView: RecyclerView
    private val bookerApiClient = BookerApiClient.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reservationRecyclerView = view.findViewById(R.id.reservation_recycler_view)
        reservationRecyclerView.adapter = BookingByIdAdapter(createItemMenuReservations())
    }

    private fun createItemMenuReservations(): MutableList<BookingById> {
        val bookingsByIds: MutableList<BookingById> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()
                val bookingIds = bookerApiClient.getBookingIds()
                if (bookingIds.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        bookingIds.body()?.forEach {
                            val response = bookerApiClient.getBookingById(it.bookingId)
                            if (response.isSuccessful) {
                                val booking = response.body()
                                if (booking != null) {
                                    bookingsByIds.add(booking)
                                }
                            }
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    e.code()
                    e.message()
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    e.message
                }
            } finally {
                ApiIdlingResource.decrement()
            }
        }
        return bookingsByIds
    }
}
