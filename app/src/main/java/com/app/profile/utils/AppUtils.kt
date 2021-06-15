package com.app.profile.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import java.util.regex.Pattern

class AppUtils {

    companion object {

        fun startActivity(context: Context, cls: Class<*>, bundle: Bundle?, isFinish: Boolean) {
            val intent = Intent(context, cls)
            bundle?.let { intent.putExtras(it) }
            context.startActivity(intent)
            when (isFinish) {
                true -> (context as AppCompatActivity).finish()
            }
        }

        fun hideSystemUI(window: Window, mainContainer: View) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, mainContainer).let { controller ->
                controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        fun isValidEmail(inputEmail: String): Boolean {
            return inputEmail.trim().lowercase()
                .isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(
                inputEmail.trim().lowercase()
            ).matches()
        }

        fun displayToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun isOnlyStringInput(input: String): Boolean {
            return isOnlyStringInput(input, true)
        }

        fun isOnlyStringInput(input: String, ignoreSpace: Boolean): Boolean {
            val regex = Pattern.compile("^[a-zA-Z]*$")
            val finalInput: String = when (ignoreSpace) {
                true -> input
                false -> input.trim()
            }
            val matcher = regex.matcher(finalInput)
            return matcher.find()
        }
    }

    /*@BindingAdapter("app:errorText")
    fun setErrorText(view: TextInputLayout, errorMessage: String) {
        if (errorMessage.isEmpty())
            view.error = null
        else
            view.error = errorMessage;
    }*/
}