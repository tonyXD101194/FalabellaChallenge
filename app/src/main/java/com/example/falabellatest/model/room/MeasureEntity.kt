package com.example.falabellatest.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measures")
data class MeasureEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "measure")
    val measure: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "value")
    val value: Double
) {

    companion object {

        private const val CODE_TAG = "codigo"
        private const val NAME_TAG = "nombre"
        private const val MEASURE_TAG = "unidad_medida"
        private const val DATE_TAG = "fecha"
        private const val VALUE_TAG = "valor"

        fun parseMeasure(title: String, map: Map<String, Any>): MeasureEntity {

            return MeasureEntity(
                title = title,
                code = map[CODE_TAG].toString(),
                name = map[NAME_TAG].toString(),
                measure = map[MEASURE_TAG].toString(),
                date = map[DATE_TAG].toString(),
                value = map[VALUE_TAG].toString().toDouble()
            )
        }
    }
}