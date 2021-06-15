package com.app.profile

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.app.profile.data.database.ProfileDatabase
import com.app.profile.data.database.dao.UserDao
import com.app.profile.data.database.entity.User
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.viewmodel.SignUpViewModel
import com.app.profile.utils.AppUtils
import com.app.profile.utils.TestCaseUtils.Companion.getBooleanValue
import com.app.profile.utils.TestCaseUtils.Companion.isValidPassword
import com.app.profile.utils.TestCaseUtils.Companion.isValidPasswordLength
import com.app.profile.utils.TestCaseUtils.Companion.testCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignUpViewModelTest {

    private lateinit var userRepository: UserRepository

    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var userDao: UserDao

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        userDao = ProfileDatabase.getDatabase(context).userDao()
        userRepository = UserRepository(userDao)
        signUpViewModel = SignUpViewModel(userRepository)
        setupTestUserForDatabaseInsertion()
    }

    private fun setupTestUserForDatabaseInsertion() {
        signUpViewModel.firstName = "Pranav"
        signUpViewModel.lastName = "Jajal"
        signUpViewModel.email = "pranav.test@gmail.com"
        signUpViewModel.phoneNumber = "9988776655"
        signUpViewModel.address = "Ahmedabad, Gujarat"
        signUpViewModel.password = "12345678"
        signUpViewModel.confirmPassword = "12345678"
    }

    @Test
    fun testFirstNameIsEmpty_ReturnTrue() {
        testCase(getBooleanValue(signUpViewModel.firstName.let { "" }), true)
    }

    @Test
    fun testFirstNameIsNotEmpty_HappyPath_ReturnTrue() {
        testCase(
            AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "Pranav" }, false),
            true
        )
    }

    @Test
    fun testFirstName_String_With_Space_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { " Pranav " }), false)
    }

    @Test
    fun testFirstName_String_With_Number_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "Pranav123" }), false)
    }

    @Test
    fun testFirstName_NumberOnly_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "123" }), false)
    }

    @Test
    fun testFirstName_String_With_SpecialCharacter_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "Pranav*#$" }), false)
    }

    @Test
    fun testFirstName_SpecialCharacterOnly_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "*#$" }), false)
    }

    @Test
    fun testFirstName_Number_With_SpecialCharacter_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.firstName.let { "123*#$" }), false)
    }

    @Test
    fun testLastNameIsEmpty_ReturnTrue() {
        testCase(getBooleanValue(signUpViewModel.lastName.let { "" }), true)
    }

    @Test
    fun testLastNameIsNotEmpty_HappyPath_ReturnTrue() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "Jajal" }, false), true)
    }

    @Test
    fun testLastName_String_With_Space_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { " Jajal " }), false)
    }

    @Test
    fun testLastName_String_With_Number_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "Jajal123" }), false)
    }

    @Test
    fun testLastName_NumberOnly_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "123" }), false)
    }

    @Test
    fun testLastName_String_With_SpecialCharacter_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "Jajal*#$" }), false)
    }

    @Test
    fun testLastName_SpecialCharacterOnly_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "*#$" }), false)
    }

    @Test
    fun testLastName_Number_With_SpecialCharacter_ReturnFalse() {
        testCase(AppUtils.isOnlyStringInput(signUpViewModel.lastName.let { "123*#$" }), false)
    }

    @Test
    fun testPasswordValidator_PasswordEmpty() {
        testCase(getBooleanValue(signUpViewModel.password.let { "" }), true)
    }

    @Test
    fun testPasswordValidator_ConfirmPasswordEmpty_ReturnTrue() {
        testCase(getBooleanValue(signUpViewModel.confirmPassword.let { "" }), true)
    }

    @Test
    fun testPasswordValidator_InvalidPasswordLength_ReturnFalse() {
        testCase(isValidPasswordLength(signUpViewModel.password.let { "1234567" }), false)
    }

    @Test
    fun testPasswordValidator_InvalidConfirmPasswordLength_ReturnFalse() {
        testCase(isValidPasswordLength(signUpViewModel.confirmPassword.let { "1234567" }), false)
    }

    @Test
    fun testPasswordValidator_BothPasswordSame_ReturnTrue() {
        testCase(
            isValidPassword(
                signUpViewModel.password.let { "12345678" },
                signUpViewModel.confirmPassword.let { "12345678" }), true
        )
    }

    @Test
    fun testPasswordValidator_BothPasswordDifferent_ReturnFalse() {
        testCase(
            isValidPassword(
                signUpViewModel.password.let { "12312312" },
                signUpViewModel.confirmPassword.let { "12345678" }), false
        )
    }

    @Test
    fun testEmailValidator_EmailEmpty_ReturnTrue() {
        testCase(getBooleanValue(signUpViewModel.email.let { "" }), true)
    }

    @Test
    fun testEmailValidator_CorrectEmail_ReturnTrue() {
        testCase(AppUtils.isValidEmail(signUpViewModel.email.let { "pranav.test@gmail.com" }), true)
    }

    @Test
    fun testEmailValidator_CorrectEmailSubDomain_ReturnTrue() {
        testCase(
            AppUtils.isValidEmail(signUpViewModel.email.let { "pranav.test@gmail.co.in" }),
            true
        )
    }

    @Test
    fun testEmailValidator_InvalidEmailNoDomain_ReturnFalse() {
        testCase(AppUtils.isValidEmail(signUpViewModel.email.let { "pranav.test@gmail" }), false)
    }

    @Test
    fun testEmailValidator_InvalidEmailDoubleDot_ReturnFalse() {
        testCase(
            AppUtils.isValidEmail(signUpViewModel.email.let { "pranav.test@gmail..com" }),
            false
        )
    }

    @Test
    fun testEmailValidator_InvalidEmailNoUsername_ReturnFalse() {
        testCase(AppUtils.isValidEmail(signUpViewModel.email.let { "@gmail.com" }), false)
    }

    @Test
    @Throws(AssertionError::class)
    fun testUser_AlreadyExists_ReturnTrue() {
        testCase(
            userDao.checkForExistingUser(signUpViewModel.email.let { "pranav.test@gmail.com" }),
            true
        )
    }

    @Test
    @Throws(AssertionError::class)
    fun testUser_DoesNotExists_ReturnFalse() {
        insertTestUser()
        testCase(
            !userDao.checkForExistingUser(signUpViewModel.email.let { "pranav.test@gmail.com" }),
            false
        )
    }

    private fun insertTestUser() {
        val user = User(
            null,
            signUpViewModel.firstName,
            signUpViewModel.lastName,
            signUpViewModel.email,
            signUpViewModel.phoneNumber,
            signUpViewModel.password,
            "",
            signUpViewModel.address
        )
        userDao.insertOrUpdateUser(user)
    }
}