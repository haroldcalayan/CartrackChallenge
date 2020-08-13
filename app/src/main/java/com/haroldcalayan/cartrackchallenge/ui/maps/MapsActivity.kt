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
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.maps.android.ktx.awaitMap
import com.haroldcalayan.cartrackchallenge.R
import com.haroldcalayan.cartrackchallenge.base.BaseActivity
import com.haroldcalayan.cartrackchallenge.data.model.User
import com.haroldcalayan.cartrackchallenge.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.bottomsheet_maps_user.*


class MapsActivity : BaseActivity<ActivityMapsBinding, MapsViewModel>() {

  private lateinit var googleMap: GoogleMap
  private lateinit var fusedLocationClient: FusedLocationProviderClient
  private lateinit var userAdapter: UserAdapter

  private var lastKnownLocation: Location? = null
  private var usersInMap = hashMapOf<Int, Marker>()

  override fun getLayout() = R.layout.activity_maps

  override fun initViews() {
    val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    lifecycle.coroutineScope.launchWhenCreated {
      googleMap = mapFragment?.awaitMap()
      initMapSettings()
      initLocation()
    }
    initBottomSheet()
    initUserListUI()
  }

  override fun initData() {
    super.initData()
    getViewModel().getUsers()
  }

  override fun subscribe() {
    super.subscribe()
    getViewModel().users.observe(this, Observer {
      userAdapter.updateList(it)
      selectUser(it[0])
    })
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
    val bottom_sheet = BottomSheetBehavior.from(linearlayout_maps_bottom_sheet)
    bottom_sheet.setState(BottomSheetBehavior.STATE_COLLAPSED)
    bottom_sheet.setBottomSheetCallback(object : BottomSheetCallback() {
      override fun onStateChanged(bottomSheet: View, newState: Int) {}
      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    })
  }

  private fun initUserListUI() {
    val linearLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
    recyclerview_maps_bottomsheet_users.layoutManager = linearLayoutManager
    recyclerview_maps_bottomsheet_users.setHasFixedSize(true)

    val listener = object: UserAdapter.Listener {
      override fun onItemClick(user: User) {
        selectUser(user)
      }
    }
    userAdapter = UserAdapter(listener, emptyList())
    recyclerview_maps_bottomsheet_users.adapter = userAdapter
  }

  private fun selectUser(user: User) {
    if(user != null) {
      textview_maps_bottomsheet_name.text = user.name
      textview_maps_bottomsheet_sub_details.text = StringBuilder()
        .append("Address: ")
        .append(if(user.address.suite.isNotEmpty()) user.address.suite + " " else "")
        .append(if(user.address.street.isNotEmpty()) user.address.street + " St " else "")
        .append(if(user.address.city.isNotEmpty()) user.address.city + " City " else "")
        .append("\nGeo: ")
        .append(user.address.geo.lat)
        .append(" lat ")
        .append(user.address.geo.lng)
        .append(" lng ")
        .toString()

      pinUserToMap(user)
    }
  }

  private fun pinUserToMap(user: User) {
    var marker : Marker? = null
    if(usersInMap.containsKey(user.id)) {
      marker = usersInMap[user.id]
    } else {
      val userLocation = user?.let { LatLng(it.address.geo.lat, it.address.geo.lng) }
      marker = googleMap.addMarker(MarkerOptions().position(userLocation).title(user.name))
      usersInMap[user.id] = marker
    }

    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker!!.position, 2f))
  }
}