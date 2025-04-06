package com.example.clinicapp.ui.confirmation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get booking details from intent
        val doctorName = intent.getStringExtra("DOCTOR_NAME")
        val patientName = intent.getStringExtra("PATIENT_NAME")
        val appointmentDate = intent.getStringExtra("APPOINTMENT_DATE")
        val appointmentTime = intent.getStringExtra("APPOINTMENT_TIME")

        // Display confirmation details
        binding.confirmationText.text = getString(
            R.string.confirmation_message,
            patientName,
            doctorName,
            appointmentDate,
            appointmentTime
        )
    }
}