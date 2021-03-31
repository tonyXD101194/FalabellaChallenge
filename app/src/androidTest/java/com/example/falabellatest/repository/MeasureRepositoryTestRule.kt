package com.example.falabellatest.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MeasureRepositoryTestRule : TestWatcher() {

    private val context: Context by lazy {

        ApplicationProvider.getApplicationContext() as Context
    }

    val measureProvider: MeasureProvider by lazy {

        MeasureRepository(context)
    }

    override fun starting(description: Description?) {
        super.starting(description)

        runBlocking {

            measureProvider.deleteAllMeasures()
        }
    }
}