package com.app.profile.utils

import org.junit.Assert

class TestCaseUtils {

    companion object {
        fun isValidPasswordLength(input: String): Boolean {
            return when (input.trim().length == AppConstants.DEFAULT_PASSWORD_LENGTH) {
                true -> true
                false -> false
            }
        }

        fun isValidPassword(password: String, confirmPassword: String): Boolean {
            return when ((password.trim().length == AppConstants.DEFAULT_PASSWORD_LENGTH && confirmPassword.trim().length == AppConstants.DEFAULT_PASSWORD_LENGTH) && (password.trim() == confirmPassword.trim())) {
                true -> true
                false -> false
            }
        }

        fun getBooleanValue(input: String): Boolean {
            return input.isEmpty()
        }

        fun testCase(condition: Boolean, assert: Boolean) {
            when (assert) {
                true -> Assert.assertTrue(condition)
                false -> Assert.assertFalse(condition)
            }
        }
    }
}