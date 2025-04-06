package com.example.clinicapp.data

data class Doctor(
    val id: Int,
    val name: String,
    val specialty: String,
    val experience: Int,
    val certifications: List<String>,
    val languages: List<String>,
    val imageResId: Int = R.drawable.ic_doctor_placeholder
)