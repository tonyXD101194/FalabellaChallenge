package com.example.falabellatest

import android.app.Application
import com.example.falabellatest.di.AppMain
import com.example.falabellatest.di.Injector

class FalabellaApp : Application() {

    lateinit var injector: AppMain

    companion object {

        private lateinit var app: FalabellaApp

        fun get(): FalabellaApp {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()

        app = this
//        injector = Injector.create(this)
    }
}