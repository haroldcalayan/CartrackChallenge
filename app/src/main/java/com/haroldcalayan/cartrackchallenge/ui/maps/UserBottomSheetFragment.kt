/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/12/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.haroldcalayan.cartrackchallenge.R

class UserBottomSheetFragment : BottomSheetDialogFragment() {

    interface Listener {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmentbottomsheet_maps_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}