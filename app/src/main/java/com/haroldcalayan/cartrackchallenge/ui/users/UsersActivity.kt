/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.users

import com.haroldcalayan.cartrackchallenge.BR
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivityUsersBinding

class UsersActivity : BaseActivity<ActivityUsersBinding, UsersViewModel>() {

    override fun getLayout() = R.layout.activity_users

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {}

    override fun initViews() {}

    override fun subscribe() {}
}