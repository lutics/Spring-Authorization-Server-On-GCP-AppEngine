package com.example.demo.android

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demo.android.databinding.MainAcivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_acivity.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_acivity), View.OnClickListener {

    private val viewModel: TestViewModel by viewModels()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        DataBindingUtil.setContentView<MainAcivityBinding>(this, R.layout.main_acivity).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin -> viewModel.login(
                host = etHost.text.toString(),
                username = etUsername.text.toString(),
                password = etPassword.text.toString()
            )
        }
    }
}