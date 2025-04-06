package com.example.clinicapp.ui.clinic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityClinicInfoBinding

class ClinicInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClinicInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClinicInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up clinic information
        binding.clinicName.text = getString(R.string.clinic_name)
        binding.clinicAddress.text = getString(R.string.clinic_address)
        binding.clinicPhone.text = getString(R.string.clinic_phone)

        // Set up implicit intents for website and location
        binding.websiteButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.clinicwebsite.com"))
            startActivity(intent)
        }

        binding.mapButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Clinic+Address"))
            startActivity(intent)
        }
    }
}