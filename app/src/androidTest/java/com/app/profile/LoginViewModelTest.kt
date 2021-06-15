package com.app.profile

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.app.profile.data.database.ProfileDatabase
import com.app.profile.data.database.dao.UserDao
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.viewmodel.LoginViewModel
import com.app.profile.utils.AppUtils
import com.app.profile.utils.TestCaseUtils.Companion.getBooleanValue
import com.app.profile.utils.TestCaseUtils.Companion.testCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var userRepository: UserRepository

    private lateinit var userDao: UserDao

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        userDao = ProfileDatabase.getDatabase(context).userDao()
        userRepository = UserRepository(userDao)
        loginViewModel = LoginViewModel(userRepository)
    }

    @Test
    fun testEmailValidator_EmailEmpty_ReturnTrue() {
        testCase(getBooleanValue(loginViewModel.email.let { "" }), true)
    }

    @Test
    fun testPasswordValidator_PasswordEmpty_ReturnTrue() {
        testCase(getBooleanValue(loginViewModel.password.let { "" }), true)
    }

    @Test
    fun testEmailValidator_CorrectEmail_ReturnTrue() {
        testCase(AppUtils.isValidEmail(loginViewModel.email.let { "pranav.test@gmail.com" }), true)
    }

    @Test
    fun testUser_InvalidCredential_ReturnFalse() {
        testCase(
            userDao.loginUser(
                loginViewModel.email.let { "pranav.test@gmail.com" },
                loginViewModel.password.let { "12340000" }), false
        )
    }

    @Test
    fun testUser_ValidCredential_ReturnTrue() {
        testCase(
            userDao.loginUser(
                loginViewModel.email.let { "pranav.test@gmail.com" },
                loginViewModel.password.let { "12345678" }), true
        )
    }
}