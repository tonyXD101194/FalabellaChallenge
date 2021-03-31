package com.example.falabellatest.database

import com.example.falabellatest.model.room.MeasureEntity

interface FalabellaDataProvider {

    companion object {

        const val DATABASE_FALABELLA_VERSION: Int = 1

        const val DATABASE_FALABELLA_NAME: String = "falabella_db.sqlite"
    }

    // region common

    suspend fun clearAllTables()

    // endregion

    // region measure CRUD

    suspend fun insertMeasure(measure: MeasureEntity): Long

    suspend fun insertMeasures(measures: List<MeasureEntity>): Array<Long>

    suspend fun getAllMeasures(): List<MeasureEntity>

    suspend fun getMeasureById(id: Long): MeasureEntity?

    suspend fun getMeasureByCode(code: String): List<MeasureEntity>

    suspend fun updateMeasure(measure: MeasureEntity)

    suspend fun deleteMeasure(measure: MeasureEntity)

    // endregion
}