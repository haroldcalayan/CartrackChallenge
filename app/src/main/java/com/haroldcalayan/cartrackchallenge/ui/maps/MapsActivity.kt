/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.coroutineScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.maps.android.ktx.awaitMap
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivityMapsBinding


class MapsActivity : BaseActivity<ActivityMapsBinding, MapsViewModel>() {

  private lateinit var googleMap: GoogleMap
  private lateinit var fusedLocationClient: FusedLocationProviderClient

  private var lastKnownLocation: Location? = null

  override fun getLayout() = R.layout.activity_maps

  override fun initViews() {
    val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    lifecycle.coroutineScope.launchWhenCreated {
      googleMap = mapFragment?.awaitMap()
      initMapSettings()
      initLocation()
    }
    initBottomSheet()
  }

  private fun initMapSettings() {
    // set map type
    googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL;
    // Enable / Disable zooming controls
    googleMap.uiSettings.isZoomControlsEnabled = false

    // Enable / Disable Compass icon
    googleMap.uiSettings.isCompassEnabled = true
    // Enable / Disable Rotate gesture
    googleMap.uiSettings.isRotateGesturesEnabled = true
    // Enable / Disable zooming functionality
    googleMap.uiSettings.isZoomGesturesEnabled = true

    googleMap.uiSettings.isScrollGesturesEnabled = true
    googleMap.uiSettings.isMapToolbarEnabled = true
  }

  private fun initLocation() {
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    if (ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      return
    }
    fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
      lastKnownLocation = location

      val lastKnownLatLng = location?.let { LatLng(it.latitude, it.longitude) }
      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, 15f))

    }
  }

  private fun initBottomSheet() {
    // get the bottom sheet view

    // get the bottom sheet view
    val llBottomSheet =
      findViewById<View>(R.id.bottom_sheet) as LinearLayout

    // init the bottom sheet behavior

    // init the bottom sheet behavior
    val bottom_sheet = BottomSheetBehavior.from(llBottomSheet)

    // change the state of the bottom sheet

    // change the state of the bottom sheet
    bottom_sheet.setState(BottomSheetBehavior.STATE_COLLAPSED)

    // set callback for changes

    // set callback for changes
    bottom_sheet.setBottomSheetCallback(object : BottomSheetCallback() {
      override fun onStateChanged(bottomSheet: View, newState: Int) {}
      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    })
  }
}