package com.example.falabellatest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

//    @Provides
//    fun provideApplication(): Application {
//        return application
//    }

    @Provides
    fun provideContext(): Context {
        return context
    }
}