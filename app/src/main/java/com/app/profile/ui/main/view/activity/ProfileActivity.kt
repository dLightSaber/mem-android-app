package com.app.profile.ui.main.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.profile.R
import com.app.profile.data.preference.PreferenceManager
import com.app.profile.databinding.ActivityProfileBinding
import com.app.profile.ui.base.ViewModelFactory
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.ui.main.viewmodel.ProfileViewModel
import com.app.profile.utils.AppConstants
import com.app.profile.utils.AppUtils

class ProfileActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViewModel()
    }

    private fun initViewModel() {
        profileViewModel =
            ViewModelProvider(viewModelStore, ViewModelFactory()).get(ProfileViewModel::class.java)
        profileViewModel.setClickListener(this)
        val userModel = profileViewModel.userRepo.getUserProfileData(
            PreferenceManager.getInstance(this).read("pref_email", "").toString()
        )
        binding.userModel = userModel
        profileViewModel.userModel = userModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuProfile -> AppUtils.startActivity(this, LoginActivity::class.java, null, true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onButtonClickListener(whichButton: Int) {
        when (whichButton) {
            AppConstants.CLICK_UPDATE_PROFILE -> {
                profileViewModel.updateUser()
            }
        }
    }
}