package com.mustafasuleymankinik.spacetraveler.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.base.BaseActivity
import com.mustafasuleymankinik.spacetraveler.databinding.ActivityUserBinding
import com.mustafasuleymankinik.spacetraveler.viewmodel.UserViewModel

class UserActivity : BaseActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = assignContentView(R.layout.activity_user) as ActivityUserBinding
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(UserViewModel::class.java)
        binding.apply {
            userActivity = this@UserActivity
            lifecycleOwner = this@UserActivity
            userViewModel = viewModel

            viewModel.success.observe(this@UserActivity, Observer {
                if (it) {
                    startActivity(Intent(this@UserActivity, MainActivity::class.java))
                    finish()
                }
            })

            viewModel.error.observe(this@UserActivity, Observer {
                if (it)
                    showError()
            })

            viewModel.speed.observe(this@UserActivity, Observer { speed ->
                viewModel.durability.observe(this@UserActivity, Observer { durability ->
                    viewModel.capability.observe(this@UserActivity, Observer { capacity ->
                        val copyCapacity = capacity
                        val copyDurability = durability
                        val copySpeed = speed
                        if (!copySpeed.isNullOrEmpty() && !copyDurability.isNullOrEmpty() && !copyCapacity.isNullOrEmpty()) {
                            tvPoint.visibility = View.VISIBLE
                            if (copySpeed.toInt() + copyDurability.toInt() + copyCapacity.toInt() > 15) {
                                tvPoint.setTextColor(
                                    ContextCompat.getColor(
                                        this@UserActivity,
                                        R.color.red
                                    )
                                )
                                btSave.visibility = View.INVISIBLE
                            } else {
                                tvPoint.setTextColor(
                                    ContextCompat.getColor(
                                        this@UserActivity,
                                        R.color.dark_gray
                                    )
                                )
                                btSave.visibility = View.VISIBLE
                            }
                            tvPoint.text =
                                (copySpeed.toInt() + copyDurability.toInt() + copyCapacity.toInt()).toString()
                        }
                    })
                })
            })
        }
    }

}