package com.example.clinicapp.ui.doctors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.data.Doctor

class DoctorsAdapter(
    private val doctors: List<Doctor>,
    private val onBookClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorsAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorImage: ImageView = itemView.findViewById(R.id.doctorImage)
        private val doctorName: TextView = itemView.findViewById(R.id.doctorName)
        private val doctorSpecialty: TextView = itemView.findViewById(R.id.doctorSpecialty)
        private val doctorExperience: TextView = itemView.findViewById(R.id.doctorExperience)
        private val doctorCertifications: TextView = itemView.findViewById(R.id.doctorCertifications)
        private val bookButton: ImageButton = itemView.findViewById(R.id.bookButton)

        fun bind(doctor: Doctor) {
            doctorImage.setImageResource(doctor.imageResId)
            doctorName.text = doctor.name
            doctorSpecialty.text = doctor.specialty
            doctorExperience.text = "${doctor.experience} years of experience"
            doctorCertifications.text = doctor.certifications.joinToString(", ")

            bookButton.setOnClickListener {
                onBookClick(doctor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(doctors[position])
    }

    override fun getItemCount(): Int = doctors.size
}