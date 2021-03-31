package com.example.falabellatest.repository

import com.example.falabellatest.model.room.MeasureEntity

interface MeasureProvider {

    // region measure

    suspend fun parsingMeasures(set: Set<Map.Entry<String, Any>>): Boolean

    suspend fun insertNewMeasure(measure: MeasureEntity)

    suspend fun getMeasures(): List<MeasureEntity>

    suspend fun getMeasureById(id: Long): MeasureEntity?

    suspend fun getMeasuresByCode(code: String): List<MeasureEntity>

    suspend fun deleteAllMeasures()

    // endregion
}