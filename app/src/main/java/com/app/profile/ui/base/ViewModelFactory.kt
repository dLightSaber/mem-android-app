@file:Suppress("UNCHECKED_CAST")

package com.app.profile.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.profile.ProfileApplication
import com.app.profile.data.repository.UserRepository
import com.app.profile.ui.main.viewmodel.LoginViewModel
import com.app.profile.ui.main.viewmodel.ProfileViewModel
import com.app.profile.ui.main.viewmodel.SignUpViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelClass = modelClass
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                ProfileApplication.instance?.getRoomDatabase()?.userDao()?.let {
                    UserRepository(
                        it
                    )
                }?.let { LoginViewModel(it) } as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                ProfileApplication.instance?.getRoomDatabase()?.userDao()?.let {
                    UserRepository(
                        it
                    )
                }?.let { SignUpViewModel(it) } as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileApplication.instance?.getRoomDatabase()?.userDao()?.let {
                    UserRepository(
                        it
                    )
                }?.let { ProfileViewModel(it) } as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}