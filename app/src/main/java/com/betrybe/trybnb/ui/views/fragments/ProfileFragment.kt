package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment() {
    private lateinit var loginInputProfile: TextInputLayout
    private lateinit var passwordInputProfile: TextInputLayout
    private lateinit var loginButtonProfile: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginInputProfile = view.findViewById(R.id.login_input_profile)
        passwordInputProfile = view.findViewById(R.id.password_input_profile)
        loginButtonProfile = view.findViewById(R.id.login_button_profile)

        loginButtonProfile.setOnClickListener {
            if (loginInputProfile.editText?.text.isNullOrEmpty()) {
                loginInputProfile.error = "O campo Login é obrigatório"
            }
            if (passwordInputProfile.editText?.text.isNullOrEmpty()) {
                passwordInputProfile.error = "O campo Password é obrigatório"
            }
        }
    }
}
