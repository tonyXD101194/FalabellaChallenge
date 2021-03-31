package com.example.falabellatest.repository

import android.content.Context
import com.example.falabellatest.database.FalabellaDataProvider
import com.example.falabellatest.database.FalabellaDatabase
import com.example.falabellatest.model.room.MeasureEntity
import java.lang.Exception

class MeasureRepository : MeasureProvider {

    constructor(context: Context, inMemoryDatabase: Boolean = false) {

        falabellaDataProvider = FalabellaDatabase(context, inMemoryDatabase)
    }

    private val falabellaDataProvider: FalabellaDataProvider

    // region measures

    override suspend fun parsingMeasures(set: Set<Map.Entry<String, Any>>): Boolean {

        return try {

            set.forEach {

                if (it.value is Map<*, *>) {

                    val value = it.value as Map<*, *>

                    val measure = MeasureEntity.parseMeasure(
                        title = it.key,
                        map = value as Map<String, Any>)

                    insertNewMeasure(measure)
                }
            }

            true
        } catch (ex: Exception) {

            false
        }
    }

    override suspend fun insertNewMeasure(measure: MeasureEntity) {

        falabellaDataProvider.insertMeasure(measure)
    }

    override suspend fun getMeasures(): List<MeasureEntity> {

        return falabellaDataProvider.getAllMeasures()
    }

    override suspend fun getMeasureById(id: Long): MeasureEntity? {

        return falabellaDataProvider.getMeasureById(id)
    }

    override suspend fun getMeasuresByCode(code: String): List<MeasureEntity> {

        return falabellaDataProvider.getMeasureByCode(code)
    }

    override suspend fun deleteAllMeasures() {

        falabellaDataProvider.clearAllTables()
    }

    // endregion
}