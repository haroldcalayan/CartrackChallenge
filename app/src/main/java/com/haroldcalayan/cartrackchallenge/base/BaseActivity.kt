/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private lateinit var viewDataBinding: T
    private var viewModel: V? = null

    /**
     * @return layout res
     */
    abstract fun getLayout(): Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * Initialize all data related task
     */
    abstract fun initData()

    /**
     * Initialize all view related task
     */
    abstract fun initViews()

    /**
     * Subscribe to live data of view model
     */
    abstract fun subscribe()

    /**
     * Return data binding of
     * the current Activity
     */
    fun getBinding(): T {
        return viewDataBinding
    }

    fun getViewModel(): V {
        return viewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initData()
        initViews()
        subscribe()
    }

    private fun provideViewModel() {
        val clazz: Class<V> = getViewModelClass()
        viewModel = ViewModelProviders.of(this).get(clazz)
    }

    private fun getViewModelClass(): Class<V> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        provideViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }
}