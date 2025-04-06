package com.example.clinicapp.ui.doctors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.data.Doctor
import com.example.clinicapp.databinding.ActivityDoctorsBinding
import com.example.clinicapp.ui.booking.BookingActivity

class DoctorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorsBinding
    private lateinit var adapter: DoctorsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadDoctors()
    }

    private fun setupRecyclerView() {
        adapter = DoctorsAdapter(emptyList()) { doctor ->
            // Handle doctor click - navigate to booking
            val intent = Intent(this, BookingActivity::class.java).apply {
                putExtra("DOCTOR_ID", doctor.id)
                putExtra("DOCTOR_NAME", doctor.name)
            }
            startActivity(intent)
        }

        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.doctorsRecyclerView.adapter = adapter
    }

    private fun loadDoctors() {
        // Sample data - in real app this would come from database/API
        val doctors = listOf(
            Doctor(
                id = 1,
                name = "Dr. Sarah Johnson",
                specialty = "Cardiologist",
                experience = 12,
                certifications = listOf("MD", "FACC"),
                languages = listOf("English", "Spanish"),
                imageResId = R.drawable.doctor1
            ),
            Doctor(
                id = 2,
                name = "Dr. Michael Chen",
                specialty = "Neurologist",
                experience = 8,
                certifications = listOf("MD", "FAAN"),
                languages = listOf("English", "Mandarin"),
                imageResId = R.drawable.doctor2
            ),
            Doctor(
                id = 3,
                name = "Dr. Priya Patel",
                specialty = "Pediatrician",
                experience = 5,
                certifications = listOf("MD", "FAAP"),
                languages = listOf("English", "Hindi", "Gujarati"),
                imageResId = R.drawable.doctor3
            )
        )

        adapter = DoctorsAdapter(doctors) { doctor ->
            val intent = Intent(this, BookingActivity::class.java).apply {
                putExtra("DOCTOR_ID", doctor.id)
                putExtra("DOCTOR_NAME", doctor.name)
            }
            startActivity(intent)
        }
        binding.doctorsRecyclerView.adapter = adapter
    }
}