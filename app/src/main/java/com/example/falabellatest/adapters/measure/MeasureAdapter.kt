package com.example.falabellatest.adapters.measure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.falabellatest.R
import com.example.falabellatest.interfaces.MeasureAction
import com.example.falabellatest.model.room.MeasureEntity

class MeasureAdapter(
    private val list: List<MeasureEntity>,
    private val callback: MeasureAction
) : RecyclerView.Adapter<MeasureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_measure, parent, false)

        return MeasureViewHolder(
            view = view,
            callback = callback
        )
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }
}