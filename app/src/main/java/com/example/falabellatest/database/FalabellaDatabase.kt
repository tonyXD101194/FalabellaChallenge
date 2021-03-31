package com.example.falabellatest.database

import android.content.Context
import com.example.falabellatest.database.room.FalabellaRoomDatabase
import com.example.falabellatest.model.room.MeasureEntity
import java.lang.StringBuilder
import java.util.concurrent.Executor

class FalabellaDatabase(
    context: Context,
    inMemoryDatabase: Boolean = false,
    executor: Executor? = null
) : FalabellaDataProvider {

    init {

        FalabellaRoomDatabase.getInstance(context, inMemoryDatabase, executor)
    }

    companion object {

        private const val PERCENT_TAG = "%"
    }

    // region common

    override suspend fun clearAllTables() {

        FalabellaRoomDatabase.clearAllTables()
    }

    // endregion

    // region measure CRUD

    override suspend fun insertMeasure(measure: MeasureEntity): Long {

        val measureSaved: MeasureEntity? = FalabellaRoomDatabase.measureDao.getMeasure(measure.title)

        return if (measureSaved == null) {

            return FalabellaRoomDatabase.measureDao.insert(measure)
        } else {

            measure.id
        }
    }

    override suspend fun insertMeasures(measures: List<MeasureEntity>): Array<Long> {

        return FalabellaRoomDatabase.measureDao.insert(measures)
    }

    override suspend fun getAllMeasures(): List<MeasureEntity> {

        return FalabellaRoomDatabase.measureDao.getAllMeasures()
    }

    override suspend fun getMeasureById(id: Long): MeasureEntity? {

        return FalabellaRoomDatabase.measureDao.getMeasureById(id)
    }

    override suspend fun getMeasureByCode(code: String): List<MeasureEntity> {

        val builder = StringBuilder()

        builder.append(PERCENT_TAG)
        builder.append(code)
        builder.append(PERCENT_TAG)

        return FalabellaRoomDatabase.measureDao.getMeasureByCode(builder.toString())
    }

    override suspend fun updateMeasure(measure: MeasureEntity) {

        FalabellaRoomDatabase.measureDao.update(measure)
    }

    override suspend fun deleteMeasure(measure: MeasureEntity) {

        FalabellaRoomDatabase.measureDao.delete(measure)
    }

    // endregion
}