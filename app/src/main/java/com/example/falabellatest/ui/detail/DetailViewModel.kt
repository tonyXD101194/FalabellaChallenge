package com.example.falabellatest.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.falabellatest.model.room.MeasureEntity
import com.example.falabellatest.repository.MeasureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val repository: MeasureRepository by lazy {

        MeasureRepository(application.applicationContext)
    }

    // region detail measure

    private val measureMutable: MutableLiveData<MeasureEntity> = MutableLiveData()

    val measure: LiveData<MeasureEntity> = this.measureMutable

    fun getMeasure(id: Long) {

        GlobalScope.launch(Dispatchers.IO) {

            val measure = repository.getMeasureById(id)

            measureMutable.postValue(measure)
        }
    }

    // endregion
}