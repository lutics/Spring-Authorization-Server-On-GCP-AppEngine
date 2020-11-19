package com.example.demo.android

import androidx.core.net.toUri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TestViewModel @ViewModelInject constructor(
    private val testClient: TestClient
) : ViewModel() {

    val result = MutableLiveData<String>()

    fun login(
        host: String,
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            testClient.login(
                url = "$host/login",
                username = username,
                password = password
            )

            val authorize = testClient.authorize(
                url = "$host/oauth2/authorize"
            )

            val location = authorize.headers()["location"]?.toUri()

            val code = location?.getQueryParameter("code")

            val token = testClient.token(
                url = "$host/oauth2/token",
                code = code
            )

            result.postValue(token)
        }
    }
}