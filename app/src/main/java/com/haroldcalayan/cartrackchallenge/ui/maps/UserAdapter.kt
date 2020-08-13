/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/14/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.ui.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.cartrackchallenge.data.model.User
import com.haroldcalayan.cartrackchallenge.databinding.AdapterUsersBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    interface Listener {
        fun onItemClick(user : User)
    }

    private val listener : Listener
    private var users : List<User>

    constructor(listener: Listener, users: List<User>) {
        this.listener = listener
        this.users = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUsersBinding.inflate(inflater)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])

        holder.itemView.setOnClickListener {
            listener.onItemClick(users[position])
        }
    }

    fun updateList(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: AdapterUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }
}