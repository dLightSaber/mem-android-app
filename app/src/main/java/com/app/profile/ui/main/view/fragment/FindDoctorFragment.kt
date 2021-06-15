package com.app.profile.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.profile.databinding.FragmentFindDoctorBinding

class FindDoctorFragment : Fragment() {

    private lateinit var binding: FragmentFindDoctorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindDoctorBinding.inflate(LayoutInflater.from(activity))
        return binding.root
    }
}