package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookerApiClient
import com.betrybe.trybnb.data.models.BookingAuthBody
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ProfileFragment : Fragment() {
    private lateinit var loginInputProfile: TextInputLayout
    private lateinit var passwordInputProfile: TextInputLayout
    private lateinit var loginButtonProfile: Button
    private lateinit var loginSuccessful: TextView
    private val bookerApiClient = BookerApiClient.instance

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
        loginSuccessful = view.findViewById(R.id.login_successful)

        loginButtonProfile.setOnClickListener {
            var isLoginOk = false
            var isPassOk = false
            val loginIn = loginInputProfile.editText?.text
            val passIn = passwordInputProfile.editText?.text

            if (loginIn.isNullOrEmpty()) {
                loginInputProfile.error = "O campo Login é obrigatório"
            } else {
                isLoginOk = true
            }
            if (passIn.isNullOrEmpty()) {
                passwordInputProfile.error = "O campo Password é obrigatório"
            } else {
                isPassOk = true
            }

            if (isLoginOk and isPassOk) toLogin(loginIn.toString(), passIn.toString())
        }
    }

    private fun toLogin(login: String, pass: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()
                val bookingAuthBody = BookingAuthBody(login, pass)
                val bookingResponse = bookerApiClient.postAuth(bookingAuthBody)
                if (bookingResponse.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        loginSuccessful.visibility = View.VISIBLE
//                        view?.findViewById<TextView>(R.id.login_successful)?.apply {
//                            text = "Login feito com sucesso!"
//                            visibility = View.VISIBLE
//                        }
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
