package com.app.profile.ui.main.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.profile.data.preference.PreferenceManager
import com.app.profile.databinding.ActivityLoginBinding
import com.app.profile.ui.base.ViewModelFactory
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.ui.main.viewmodel.LoginViewModel
import com.app.profile.utils.AppConstants
import com.app.profile.utils.AppUtils

class LoginActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel() {
        loginViewModel =
            ViewModelProvider(viewModelStore, ViewModelFactory()).get(LoginViewModel::class.java)
        loginViewModel.setClickListener(this)
        binding.loginViewModel = loginViewModel
        observeData()
    }

    private fun observeData() {
        loginViewModel.errorData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onButtonClickListener(whichButton: Int) {
        when (whichButton) {
            AppConstants.CLICK_LOGIN -> {
                when (loginViewModel.status) {
                    true -> {
                        PreferenceManager.getInstance(this)
                            .write("pref_email", loginViewModel.email)
                        AppUtils.startActivity(this, MainActivity::class.java, null, true)
                    }
                    false -> loginViewModel.errorData.value?.let {
                        AppUtils.displayToast(
                            this,
                            it
                        )
                    }
                }
            }
            AppConstants.CLICK_TEXT_SIGN_UP -> {
                AppUtils.startActivity(this, SignUpActivity::class.java, null, false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        AppUtils.hideSystemUI(window, binding.mainContainer)
    }
}