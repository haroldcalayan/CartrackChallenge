/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/11/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.signin

import android.app.Activity
import androidx.lifecycle.Observer
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivitySigninBinding

class SignInActivity : BaseActivity<ActivitySigninBinding, SignInViewModel>() {

    override fun getLayout() = R.layout.activity_signin

    override fun subscribe() {
        super.subscribe()

        getViewModel().loading.observe(this, Observer {

        })

        getViewModel().loginSuccess.observe(this, Observer {
            setResult(Activity.RESULT_OK)
            finish()
        })

        getViewModel().errorMessage.observe(this, Observer {
            showToastMessage(it)
        })

        getViewModel().close.observe(this, Observer {
            setResult(Activity.RESULT_CANCELED)
            finish()
        })
    }
}