/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.login

import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivityLoginBinding
import com.haroldcalayan.cartrackchallenge.ui.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),
    CountryBottomSheetFragment.Listener {

    private var seekPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seekPosition = savedInstanceState.getInt("position")
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("position", videoview_login_background.getCurrentPosition())
        videoview_login_background.pause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        seekPosition = savedInstanceState.getInt("position")
    }

    override fun onPause() {
        super.onPause()
        videoview_login_background.suspend()
    }

    override fun onResume() {
        super.onResume()
        videoview_login_background.seekTo(seekPosition)
        videoview_login_background.resume()
    }

    override fun getLayout() = R.layout.activity_login

    override fun initViews() {
        super.initViews()
        initVideo()

        button_login_signin.setOnClickListener {
            startSignIn()
        }

        textview_login_country.setOnClickListener {
            val bottomSheetFragment = CountryBottomSheetFragment(this)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun onCountryClick(country: String) {
        textview_login_country.text = country
    }

    private fun initVideo() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoview_login_background)
        videoview_login_background.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.login_background_mute)
        )

        videoview_login_background.requestFocus()
        videoview_login_background.setOnPreparedListener(OnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            videoview_login_background.seekTo(seekPosition)
            if (seekPosition == 0) {
                videoview_login_background.start()
            } else {
                videoview_login_background.pause()
            }
        })
    }

    private fun startSignIn() {
        Intent(this, SignInActivity::class.java).apply {
            startActivity(this)
        }
    }
}