package com.example.falabellatest.model

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.falabellatest.model.development.provider.TestResourceProvider
import com.example.falabellatest.model.room.MeasureEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MeasureEntityTest {

    @Test
    fun parseMeasureSuccessTest() {

        val mapResponse = TestResourceProvider.getMapObject("measures/measure_test_01.json")

        val result: MeasureEntity = MeasureEntity.parseMeasure("title", mapResponse["uf"] as Map<String, Any>)

        assertThat(result).isNotNull()
    }
}