package com.example.falabellatest.model.development.testdatabuilder

import androidx.annotation.VisibleForTesting
import com.example.falabellatest.model.room.MeasureEntity

@VisibleForTesting
class MeasureBuilder private constructor() {

    private var id: Long = 0
    private var title: String = ""
    private var code: String = ""
    private var name: String = ""
    private var measure: String = ""
    private var date: String = ""
    private var value: Double = 0.0

    fun withId(value: Long): MeasureBuilder {

        this.id = value
        return this
    }

    fun withTitle(value: String): MeasureBuilder {

        this.title = value
        return this
    }

    fun withCode(value: String): MeasureBuilder {

        this.code = value
        return this
    }

    fun withName(value: String): MeasureBuilder {

        this.name = value
        return this
    }

    fun withMeasure(value: String): MeasureBuilder {

        this.measure = value
        return this
    }

    fun withDate(value: String): MeasureBuilder {

        this.date = value
        return this
    }

    fun withValue(value: Double): MeasureBuilder {

        this.value = value
        return this
    }

    fun build(): MeasureEntity {

        return MeasureEntity(
            id = this.id,
            title = this.title,
            name = this.name,
            measure = this.measure,
            date = this.date,
            value = this.value,
            code = this.code
        )
    }

    companion object {

        fun aMeasure(): MeasureBuilder {

            return MeasureBuilder()
        }
    }
}