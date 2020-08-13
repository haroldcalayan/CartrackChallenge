/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/14/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.data.model.User
import com.haroldcalayan.cartrackchallenge.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.lang.StringBuilder


class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    private val gson = Gson()
    private lateinit var user: User

    override fun getLayout() = R.layout.activity_details

    override fun initViews() {
        super.initViews()
        initToolbar()
    }

    override fun initData() {
        super.initData()
        user = gson.fromJson(intent.getStringExtra(EXTRA_USER), User::class.java)
        updateUI()
    }

    override fun subscribe() {
        super.subscribe()
        getViewModel().fab.observe(this, Observer {
            showSnackBarMessage(fab, R.string.details_warning_feature_is_not_yet_implemented)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun updateUI() {
        toolbar_layout.title = user.name
        textview_details_basic_info.text = StringBuilder()
            .append("Username: ")
            .append(user.username)
            .append("\nWebsite: ")
            .append(user.website)
            .toString()
        textview_details_address.text = StringBuilder()
            .append("Address: ")
            .append(user.address.suite)
            .append(" ")
            .append(user.address.street)
            .append(" St. ")
            .append(user.address.city)
            .append(" ")
            .append(user.address.zipcode)
            .append("\nGeo: ")
            .append(user.address.geo.lat)
            .append(" lat ")
            .append(user.address.geo.lng)
            .append(" lng")
            .toString()
        textview_details_contacts.text = StringBuilder()
            .append("Phone: ")
            .append(user.phone)
            .append("\nEmail: ")
            .append(user.email)
            .toString()
        textview_details_work.text = StringBuilder()
            .append("Company Name: ")
            .append(user.company.name)
            .append("\nCatch Phrase: ")
            .append(user.company.catchPhrase)
            .append("\nBS: ")
            .append(user.company.bs)
            .toString()
    }

    companion object {
        const val EXTRA_USER = "user"
    }
}