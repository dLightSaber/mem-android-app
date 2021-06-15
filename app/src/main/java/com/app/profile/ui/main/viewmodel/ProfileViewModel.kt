package com.app.profile.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.app.profile.data.database.entity.User
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.utils.AppConstants

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    private lateinit var clickListener: ClickListener
    lateinit var userModel: User
    val userRepo = this.userRepository

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    fun onUpdateProfileClick() {
        clickListener.onButtonClickListener(AppConstants.CLICK_UPDATE_PROFILE)
    }

    fun updateUser() {
        userRepository.insertOrUpdateUser(userModel)
    }
}