package com.app.mychat.models.modelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mychat.models.viewmodels.MessagesViewModel

//on startup of application - not to be accessed by a function
class ChatViewModelFactory(private val application: Application,private val someString: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MessagesViewModel(
        application,
        someString
    ) as T
}


