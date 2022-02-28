package com.mustafasuleymankinik.spacetraveler.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mustafasuleymankinik.spacetraveler.R

open class BaseFragment : Fragment() {

    fun getBaseActivity(): BaseActivity? {
        if (activity is BaseActivity)
            return activity as BaseActivity
        return null
    }

    fun showLoading() {
        getBaseActivity()?.showLoading()
    }

    fun hideLoading() {
        getBaseActivity()?.hideLoading()
    }

    fun showError(errorTitle: String? = null, errorMessage: String? = null) {
        getBaseActivity()?.showError(errorTitle, errorMessage)
    }

}