/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/11/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.haroldcalayan.cartrackchallenge.R


class CountryBottomSheetFragment(val listener: Listener) : BottomSheetDialogFragment() {

    interface Listener {
        fun onCountryClick(country: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_login_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.linearlayout_countries_philippines).setOnClickListener {
            this@CountryBottomSheetFragment.dismiss()
            listener.onCountryClick("Philippines")
        }
        view.findViewById<LinearLayout>(R.id.linearlayout_countries_singapore).setOnClickListener {
            this@CountryBottomSheetFragment.dismiss()
            listener.onCountryClick("Singapore")
        }
        view.findViewById<LinearLayout>(R.id.linearlayout_countries_australia).setOnClickListener {
            this@CountryBottomSheetFragment.dismiss()
            listener.onCountryClick("Australia")
        }
    }
}