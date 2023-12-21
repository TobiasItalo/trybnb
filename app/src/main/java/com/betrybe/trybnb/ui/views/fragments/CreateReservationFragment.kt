package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment() {
    private lateinit var firstNameCreateReservation: TextInputLayout
    private lateinit var lastNameCreateReservation: TextInputLayout
    private lateinit var checkinCreateReservation: TextInputLayout
    private lateinit var checkoutCreateReservation: TextInputLayout
    private lateinit var additionalNeedsCreateReservation: TextInputLayout
    private lateinit var totalPriceCreateReservation: TextInputLayout

    private lateinit var createReservationButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNameCreateReservation = view.findViewById(R.id.first_name_create_reservation)
        lastNameCreateReservation = view.findViewById(R.id.last_name_create_reservation)
        checkinCreateReservation = view.findViewById(R.id.checkin_create_reservation)
        checkoutCreateReservation = view.findViewById(R.id.checkout_create_reservation)
        additionalNeedsCreateReservation =
            view.findViewById(R.id.additional_needs_create_reservation)
        totalPriceCreateReservation = view.findViewById(R.id.total_price_create_reservation)
        createReservationButton = view.findViewById(R.id.create_reservation_button)

        createReservationButton.setOnClickListener {
            if (firstNameCreateReservation.editText?.text.isNullOrEmpty()) {
                firstNameCreateReservation.error = "O campo Nome é obrigatório"
            }
            if (lastNameCreateReservation.editText?.text.isNullOrEmpty()) {
                lastNameCreateReservation.error = "O campo Sobrenome é obrigatório"
            }
            if (checkinCreateReservation.editText?.text.isNullOrEmpty()) {
                checkinCreateReservation.error = "O campo Checkin é obrigatório"
            }
            if (checkoutCreateReservation.editText?.text.isNullOrEmpty()) {
                checkoutCreateReservation.error = "O campo Checkout é obrigatório"
            }
            if (additionalNeedsCreateReservation.editText?.text.isNullOrEmpty()) {
                additionalNeedsCreateReservation
                    .error = "O campo Necessidades Adicionais é obrigatório"
            }
            if (totalPriceCreateReservation.editText?.text.isNullOrEmpty()) {
                totalPriceCreateReservation.error = "O campo Preço Total é obrigatório"
            }
        }
    }
}
