package com.example.clinicapp.ui.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityBookingBinding
import java.util.Calendar

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get doctor info from intent
        val doctorName = intent.getStringExtra("DOCTOR_NAME")
        binding.doctorName.text = getString(R.string.booking_for_doctor, doctorName)

        // Set up date and time pickers
        setupDateTimePickers()
        
        // Set up form validation and submission
        setupForm()
    }

    private fun setupDateTimePickers() {
        val calendar = Calendar.getInstance()
        
        // Date Picker
        binding.datePickerButton.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    selectedDate = "$day/${month + 1}/$year"
                    binding.datePickerButton.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Time Picker
        binding.timePickerButton.setOnClickListener {
            TimePickerDialog(
                this,
                { _, hour, minute ->
                    selectedTime = String.format("%02d:%02d", hour, minute)
                    binding.timePickerButton.text = selectedTime
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setupForm() {
        binding.submitButton.setOnClickListener {
            if (validateForm()) {
                // Form is valid, proceed with booking
                Toast.makeText(this, R.string.booking_successful, Toast.LENGTH_SHORT).show()
                // Here you would typically save the booking or navigate to confirmation
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Validate patient name
        if (binding.patientName.text.toString().trim().isEmpty()) {
            binding.patientName.error = getString(R.string.error_empty_name)
            isValid = false
        } else {
            binding.patientName.error = null
        }

        // Validate phone number
        if (binding.patientPhone.text.toString().trim().isEmpty()) {
            binding.patientPhone.error = getString(R.string.error_empty_phone)
            isValid = false
        } else {
            binding.patientPhone.error = null
        }

        // Validate appointment type
        if (binding.appointmentTypeGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, R.string.error_select_appointment_type, Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Validate date and time
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, R.string.error_select_date, Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (selectedTime.isEmpty()) {
            Toast.makeText(this, R.string.error_select_time, Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }
}