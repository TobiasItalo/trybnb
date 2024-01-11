package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookerApiClient
import com.betrybe.trybnb.data.models.BookingById
import com.betrybe.trybnb.data.models.BookingIds
import com.betrybe.trybnb.ui.adapters.BookingByIdAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

private const val MAX_RESERVATIONS = 10

class ReservationFragment : Fragment() {
    private lateinit var reservationRecyclerView: RecyclerView
    private val bookerApiClient = BookerApiClient.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        reservationRecyclerView = view.findViewById(R.id.reservation_recycler_view)
        reservationRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onResume() {
        super.onResume()
        createItemMenuReservations()
    }

    private fun createItemMenuReservations() {
        val bookingsReservations: MutableList<BookingById> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            val bookingsByIds = createListIdsReservations()
            bookingsByIds.forEach {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        ApiIdlingResource.increment()
                        val itemsMenuReservations =
                            bookerApiClient.getBookingById("application/json", it.bookingId)
                        if (itemsMenuReservations.isSuccessful) {
                            bookingsReservations.add(itemsMenuReservations.body()!!)
                            withContext(Dispatchers.Main) {
                                reservationRecyclerView.adapter =
                                    BookingByIdAdapter(bookingsReservations)
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Snackbar.make(
                                    requireView(),
                                    "Falha ao requerer ids das reservas!",
                                    Snackbar.LENGTH_LONG,
                                ).show()
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
            }
        }
    }

    private suspend fun createListIdsReservations(): List<BookingIds> {
        var bookingsByIds: List<BookingIds> = listOf()
        withContext(Dispatchers.IO) {
            try {
                ApiIdlingResource.increment()
                val bookingIds = bookerApiClient.getBookingIds()
                if (bookingIds.isSuccessful) {
                    bookingsByIds =
                        bookingIds.body()!!.take(MAX_RESERVATIONS)
                } else {
                    withContext(Dispatchers.Main) {
                        Snackbar.make(
                            requireView(),
                            "Falha ao requerer ids das reservas!",
                            Snackbar.LENGTH_LONG,
                        ).show()
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
