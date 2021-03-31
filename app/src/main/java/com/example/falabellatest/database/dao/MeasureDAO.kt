package com.example.falabellatest.database.dao

import androidx.room.*
import com.example.falabellatest.model.room.MeasureEntity

@Dao
interface MeasureDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(measure: MeasureEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(measures: List<MeasureEntity>): Array<Long>

    @Query("SELECT * FROM measures")
    suspend fun getAllMeasures(): List<MeasureEntity>

    @Query("SELECT * FROM measures WHERE id = :id")
    suspend fun getMeasureById(id: Long): MeasureEntity?

    @Query("SELECT * FROM measures WHERE title = :title")
    suspend fun getMeasure(title: String): MeasureEntity?

    @Query("SELECT * FROM measures WHERE code LIKE :code")
    suspend fun getMeasureByCode(code: String): List<MeasureEntity>

    @Update
    suspend fun update(measure: MeasureEntity)

    @Delete
    suspend fun delete(measure: MeasureEntity)
}