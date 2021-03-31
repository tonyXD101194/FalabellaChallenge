package com.example.falabellatest.di

import com.example.falabellatest.ui.MainActivity
import com.example.falabellatest.ui.detail.DetailFragment
import com.example.falabellatest.ui.main.MainFragment

interface AppMain {

    fun inject(activity: MainActivity)

    fun inject(fragment: MainFragment)

    fun inject(fragment: DetailFragment)
}