package com.example.falabellatest.database

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.falabellatest.model.development.testdatabuilder.MeasureBuilder
import com.example.falabellatest.model.room.MeasureEntity
import kotlinx.coroutines.runBlocking
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.*

class FalabellaDatabaseTestRule : TestWatcher() {

    val context: Context by lazy {

        ApplicationProvider.getApplicationContext() as Context
    }

    // region repository

    val falabellaDataProvider: FalabellaDataProvider by lazy {

        val application: Application = ApplicationProvider.getApplicationContext()

        FalabellaDatabase(application, true)
    }

    // endregion

    // region models

    val measure: MeasureEntity by lazy {

        val timestamp: Long = Date().time

        MeasureBuilder
            .aMeasure()
            .withTitle("test title $timestamp")
            .withName("test name $timestamp")
            .withDate("test date $timestamp")
            .withMeasure("test measure $timestamp")
            .withCode("test code $timestamp")
            .withValue(0.0)
            .build()
    }

    fun getMeasures(): List<MeasureEntity> {

        val mutableList: MutableList<MeasureEntity> = mutableListOf()

        for (i in 0..2) {

            mutableList.add(measure)
        }

        return mutableList
    }

    // endregion

    override fun finished(description: Description?) {
        super.finished(description)

        runBlocking {

            falabellaDataProvider.clearAllTables()
        }
    }
}