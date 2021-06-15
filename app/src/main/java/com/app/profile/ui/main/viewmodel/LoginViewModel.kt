package com.app.profile.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.utils.AppConstants
import com.app.profile.utils.AppUtils
import com.app.profile.utils.Coroutines

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    var email: String = ""
    var password: String = ""
    var errorData: MutableLiveData<String> = MutableLiveData()
    private lateinit var clickListener: ClickListener
    var status: Boolean = false

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    private fun isValidInput(): Boolean {
        when {
            email.isEmpty() -> errorData.value = "Email is empty."
            password.isEmpty() -> errorData.value = "Password is empty."
            !isValidEmailInput() -> errorData.value = "Invalid email."
        }
        return AppUtils.isValidEmail(email) && password.isNotEmpty()
    }

    private fun isValidEmailInput(): Boolean {
        return email.isNotEmpty() && AppUtils.isValidEmail(email)
    }

    fun onLoginButtonClick() {
        invalidateInput()
    }

    fun onSignUpClick() {
        clickListener.onButtonClickListener(AppConstants.CLICK_TEXT_SIGN_UP)
    }

    private fun invalidateInput() {
        Coroutines.main {
            val exist = userRepository.loginUser(email, password)
            when (exist) {
                false -> errorData.value = "Invalid credential."
            }
            status = isValidInput() && exist
            clickListener.onButtonClickListener(AppConstants.CLICK_LOGIN)
        }
    }
}

