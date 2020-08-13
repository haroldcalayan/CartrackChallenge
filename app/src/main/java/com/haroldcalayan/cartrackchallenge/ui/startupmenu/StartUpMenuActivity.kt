/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.startupmenu

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivityStartUpMenuBinding
import com.haroldcalayan.cartrackchallenge.ui.maps.MapsActivity
import com.haroldcalayan.cartrackchallenge.ui.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_start_up_menu.*

class StartUpMenuActivity : BaseActivity<ActivityStartUpMenuBinding, StartUpMenuViewModel>(),
    CountryBottomSheetFragment.Listener {

    private var seekPosition = 0

    override fun getLayout() = R.layout.activity_start_up_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seekPosition = savedInstanceState.getInt("position")
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("position", videoview_startupmenu_background.getCurrentPosition())
        videoview_startupmenu_background.pause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        seekPosition = savedInstanceState.getInt("position")
    }

    override fun onPause() {
        super.onPause()
        videoview_startupmenu_background.suspend()
    }

    override fun onResume() {
        super.onResume()
        videoview_startupmenu_background.seekTo(seekPosition)
        videoview_startupmenu_background.resume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE_SIGNIN && resultCode === Activity.RESULT_OK) {
            startMaps()
            finish()
        }
    }

    override fun initViews() {
        super.initViews()
        initVideo()

        textview_startupmenu_country.setOnClickListener {
            val bottomSheetFragment = CountryBottomSheetFragment(this)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun onCountryClick(country: String) {
        textview_startupmenu_country.text = country
    }

    fun onSignInClick(view: View) = startSignIn()

    fun onSignUpClick(view: View) {}

    private fun initVideo() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoview_startupmenu_background)
        videoview_startupmenu_background.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.login_background_mute)
        )

        videoview_startupmenu_background.requestFocus()
        videoview_startupmenu_background.setOnPreparedListener(OnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            videoview_startupmenu_background.seekTo(seekPosition)
            if (seekPosition == 0) {
                videoview_startupmenu_background.start()
            } else {
                videoview_startupmenu_background.pause()
            }
        })
    }

    private fun startSignIn() {
        Intent(this, SignInActivity::class.java).apply {
            startActivityForResult(this, REQUEST_CODE_SIGNIN)
        }
    }

    private fun startMaps() {
        Intent(this, MapsActivity::class.java).apply {
            startActivity(this)
        }
    }

    companion object {
        const val REQUEST_CODE_SIGNIN = 1000
    }
}