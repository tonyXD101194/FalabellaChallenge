package com.example.falabellatest.adapters.measure

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.falabellatest.interfaces.MeasureAction
import com.example.falabellatest.model.room.MeasureEntity
import kotlinx.android.synthetic.main.item_measure.view.*

class MeasureViewHolder(
    val view: View,
    private val callback: MeasureAction
) : RecyclerView.ViewHolder(view) {

    fun bind(measure: MeasureEntity) {

        view.itemMeasureTextViewTitle.text = measure.title

        view.itemMeasureTextViewSubtitle.text = measure.value.toString()

        view.setOnClickListener {

            callback.onClickMeasure(measure.id)
        }
    }
}