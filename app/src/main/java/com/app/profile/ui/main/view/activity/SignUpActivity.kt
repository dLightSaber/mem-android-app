package com.app.profile.ui.main.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.profile.data.database.entity.User
import com.app.profile.databinding.ActivitySignupBinding
import com.app.profile.ui.base.ViewModelFactory
import com.app.profile.ui.main.callbacks.ClickListener
import com.app.profile.ui.main.viewmodel.SignUpViewModel
import com.app.profile.utils.AppConstants
import com.app.profile.utils.AppUtils
import com.app.profile.utils.Coroutines

class SignUpActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel() {
        signUpViewModel =
            ViewModelProvider(viewModelStore, ViewModelFactory()).get(SignUpViewModel::class.java)
        signUpViewModel.setClickListener(this)
        binding.signUpViewModel = signUpViewModel
        observeData()
    }

    private fun observeData() {
        signUpViewModel.errorData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onButtonClickListener(whichButton: Int) {
        when (whichButton) {
            AppConstants.CLICK_SIGN_UP -> {
                when (signUpViewModel.status) {
                    true -> {
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
                        Coroutines.io { signUpViewModel.insertUser(user) }
                        AppUtils.startActivity(this, ProfileActivity::class.java, null, true)

                        //Coroutines.io { signUpViewModel.deleteAllUsers() }
                    }
                    false -> signUpViewModel.errorData.value?.let {
                        AppUtils.displayToast(
                            this,
                            it
                        )
                    }
                }
            }
            AppConstants.CLICK_TEXT_SIGN_IN -> finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.hide()
        AppUtils.hideSystemUI(window, binding.mainContainer)
    }
}