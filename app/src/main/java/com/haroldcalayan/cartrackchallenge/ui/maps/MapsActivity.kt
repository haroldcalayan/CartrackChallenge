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
import androidx.core.app.ActivityCompat
import androidx.lifecycle.coroutineScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.awaitMap
import com.haroldcalayan.cartrackchallenge.BR
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.databinding.ActivityMapsBinding

class MapsActivity : BaseActivity<ActivityMapsBinding, MapsViewModel>() {

  private lateinit var googleMap: GoogleMap
  private lateinit var fusedLocationClient: FusedLocationProviderClient

  private var lastKnownLocation: Location? = null

  override fun getLayout() = R.layout.activity_maps

  override fun getBindingVariable() = BR.viewModel

  override fun initData() {}

  override fun initViews() {
    val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    lifecycle.coroutineScope.launchWhenCreated {
      googleMap = mapFragment?.awaitMap()
      initLocation()
    }
  }

  override fun subscribe() {}

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
}