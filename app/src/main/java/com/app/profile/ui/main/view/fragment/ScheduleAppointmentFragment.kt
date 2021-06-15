package com.app.profile.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.profile.databinding.FragmentScheduleAppointmentBinding

class ScheduleAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentScheduleAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleAppointmentBinding.inflate(LayoutInflater.from(activity))
        return binding.root
    }
}