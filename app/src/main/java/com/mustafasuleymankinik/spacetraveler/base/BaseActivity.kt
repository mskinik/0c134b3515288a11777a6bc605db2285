package com.mustafasuleymankinik.spacetraveler.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {
    private lateinit var baseBinding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
    }

    fun assignContentView(layoutResID: Int): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, layoutResID, baseBinding.frame, true)
    }

    fun showLoading() {
        baseBinding.clLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        baseBinding.clLoading.visibility = View.GONE
    }

    fun showError(
        errorTitle: String? = "Kesinlikle Hiç Beklenmedik Bir Hata!",
        errorMessage: String? = "Bende beklemiyordum ama olsun, girdiğin değerleri kontrol etsen iyi olur"
    ) {
        val builder = AlertDialog.Builder(this)
            .setTitle(errorTitle)
            .setMessage(errorMessage)
            .setPositiveButton("Kapat", null)
            .setCancelable(false)
            .show()
    }
}