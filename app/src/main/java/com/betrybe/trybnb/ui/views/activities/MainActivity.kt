package com.betrybe.trybnb.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.trybnb.R
import com.betrybe.trybnb.ui.views.fragments.CreateReservationFragment
import com.betrybe.trybnb.ui.views.fragments.ProfileFragment
import com.betrybe.trybnb.ui.views.fragments.ReservationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val btnNavigationView: BottomNavigationView by lazy {
        findViewById(R.id.navigation_bottom_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.reservation_menu_item -> ReservationFragment()
                R.id.create_reservation_menu_item -> CreateReservationFragment()
                R.id.profile_menu_tem -> ProfileFragment()
                else -> null
            }

            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit()
            }

            true
        }
    }
}
