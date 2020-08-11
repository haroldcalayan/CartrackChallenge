/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/11/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.signin

import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivitySigninBinding
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : BaseActivity<ActivitySigninBinding, SignInViewModel>() {

    override fun getLayout() = R.layout.activity_signin

    override fun initViews() {
        super.initViews()

        textinputedittext_signin_username.text
    }
}