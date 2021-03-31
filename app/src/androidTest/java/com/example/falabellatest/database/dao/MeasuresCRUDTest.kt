package com.example.falabellatest.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.falabellatest.database.FalabellaDatabaseTestRule
import com.example.falabellatest.model.room.MeasureEntity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MeasuresCRUDTest {

    @get:Rule
    val rule = FalabellaDatabaseTestRule()

    @Test
    fun insertMeasureTest() = runBlocking {

        val measure: MeasureEntity = rule.measure

        val result = rule.falabellaDataProvider.insertMeasure(measure)

        assertThat(result).isGreaterThan(0)
    }

    @Test
    fun insertMeasureListTest() = runBlocking {

        val measures: List<MeasureEntity> = rule.getMeasures()

        val result = rule.falabellaDataProvider.insertMeasures(measures)

        assertThat(result.none { x -> x == (-1).toLong() }).isTrue()
        assertThat(result.isNotEmpty()).isTrue()
        assertThat(result.size == 3).isTrue()
    }

    @Test
    fun getAllMeasuresTest() = runBlocking {

        val measures: List<MeasureEntity> = rule.getMeasures()

        rule.falabellaDataProvider.insertMeasures(measures)

        val result = rule.falabellaDataProvider.getAllMeasures()

        assertThat(result.isNotEmpty()).isTrue()
        assertThat(result.size).isGreaterThan(0)
    }

    @Test
    fun getMeasureByIdTest() = runBlocking {

        val measures: List<MeasureEntity> = rule.getMeasures()

        rule.falabellaDataProvider.insertMeasures(measures)

        val measuresSaved = rule.falabellaDataProvider.getAllMeasures()

        val result = rule.falabellaDataProvider.getMeasureById(measuresSaved[0].id)

        assertThat(result).isNotNull()
        assertThat(result?.code).isEqualTo(rule.measure.code)
    }

    @Test
    fun getMeasureByCodeTest() = runBlocking {

        val measures: List<MeasureEntity> = rule.getMeasures()

        rule.falabellaDataProvider.insertMeasures(measures)

        val result = rule.falabellaDataProvider.getMeasureByCode(rule.measure.code)

        assertThat(result.isNotEmpty()).isTrue()
        assertThat(result.size).isGreaterThan(0)
    }

    @Test
    fun updateTest() = runBlocking {

        val measure: MeasureEntity = rule.measure

        val id = rule.falabellaDataProvider.insertMeasure(measure)

        val measureSaved = rule.falabellaDataProvider.getMeasureById(id)

        rule.falabellaDataProvider.updateMeasure(measureSaved!!.copy(
            code = "code update"
        ))

        val result = rule.falabellaDataProvider.getMeasureByCode("code update")

        assertThat(result.isNotEmpty()).isTrue()
        assertThat(result.size).isGreaterThan(0)
    }

    @Test
    fun deleteTest() = runBlocking {

        val measure: MeasureEntity = rule.measure

        val id = rule.falabellaDataProvider.insertMeasure(measure)

        val measureSaved = rule.falabellaDataProvider.getMeasureById(id)

        rule.falabellaDataProvider.deleteMeasure(measureSaved!!)

        val result = rule.falabellaDataProvider.getMeasureById(id)

        assertThat(result).isNull()
    }
}