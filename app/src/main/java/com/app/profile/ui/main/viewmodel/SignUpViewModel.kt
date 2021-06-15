package com.app.profile.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.profile.data.database.entity.User
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.utils.AppConstants
import com.app.profile.utils.AppUtils
import com.app.profile.utils.Coroutines

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    var address: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var errorData: MutableLiveData<String> = MutableLiveData()
    private lateinit var clickListener: ClickListener
    var status: Boolean = false

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    private fun isValidInput(): Boolean {
        when {
            firstName.isEmpty() -> errorData.value = "First Name is empty."
            lastName.isEmpty() -> errorData.value = "Last Name is empty."
            email.isEmpty() -> errorData.value = "Email is empty."
            phoneNumber.isEmpty() -> errorData.value = "Phone Number is empty."
            password.isEmpty() -> errorData.value = "Password is empty."
            confirmPassword.isEmpty() -> errorData.value = "Confirm Password is empty."
            !isValidEmailInput() -> errorData.value = "Invalid email."
            !isValidPasswordInput() -> errorData.value = "Password length must be 8 characters."
            !isEqualPassword() -> errorData.value = "Both password should be same."
        }
        return firstName.isNotEmpty() && lastName.isNotEmpty() && isValidEmailInput() && isValidPasswordInput() && isEqualPassword()
    }

    private fun isValidEmailInput(): Boolean {
        return email.isNotEmpty() && AppUtils.isValidEmail(email)
    }

    private fun isValidPasswordInput(): Boolean {
        return password.isNotEmpty() && confirmPassword.isNotEmpty() && password.length == 8 && confirmPassword.length == 8 && password == confirmPassword
    }

    private fun isEqualPassword(): Boolean {
        return isValidPasswordInput() && password == confirmPassword
    }

    fun onSignInClick() {
        clickListener.onButtonClickListener(AppConstants.CLICK_TEXT_SIGN_IN)
    }

    fun onSignUpClick() {
        invalidateInput()
    }

    private fun invalidateInput() {
        Coroutines.main {
            val exist = userRepository.getExistingUser(email)
            when (exist) {
                true -> errorData.value = "User already exists."
            }
            status = isValidInput() && !exist
            clickListener.onButtonClickListener(AppConstants.CLICK_SIGN_UP)
        }
    }

    fun insertUser(user: User) {
        userRepository.insertOrUpdateUser(user)
    }

    fun deleteUserFromDataBase(id: Int) {
        userRepository.deleteUserFromDataBase(id)
    }

    fun deleteAllUsers() {
        userRepository.deleteAll()
    }
}