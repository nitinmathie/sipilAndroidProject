package com.example.hopelastrestart1.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.example.hopelastrestart1.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
