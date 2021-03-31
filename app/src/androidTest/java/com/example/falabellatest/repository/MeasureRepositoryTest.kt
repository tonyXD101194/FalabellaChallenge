package com.example.falabellatest.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.falabellatest.model.development.provider.TestResourceProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MeasureRepositoryTest {

    @get:Rule
    val rule = MeasureRepositoryTestRule()

    @Test
    fun saveMeasuresTest() = runBlocking {

        val map = TestResourceProvider.getMapObject("measures/measure_test_03.json")

        val result: Boolean = rule.measureProvider.parsingMeasures(map.entries)

        assertThat(result).isTrue()
    }
}