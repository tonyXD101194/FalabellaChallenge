package com.example.falabellatest.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.falabellatest.R
import com.example.falabellatest.api.ApiService
import com.example.falabellatest.api.ApiServiceInterface
import com.example.falabellatest.model.room.MeasureEntity
import com.example.falabellatest.repository.MeasureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val service: ApiServiceInterface by lazy {

        ApiService.getApiService().create(ApiServiceInterface::class.java)
    }

    private val repository: MeasureRepository by lazy {

        MeasureRepository(application.applicationContext)
    }

    // region messages

    private val messageIntMutable: MutableLiveData<Int> = MutableLiveData()

    val message: LiveData<Int> = this.messageIntMutable

    // endregion

    // region get measures

    private val measuresMutable: MutableLiveData<List<MeasureEntity>> = MutableLiveData()

    val measures: LiveData<List<MeasureEntity>> = this.measuresMutable

    fun getMeasures() {

        GlobalScope.launch(Dispatchers.IO) {

            val call: Call<Map<String, Any>> = service.getMeasures()

            call.enqueue(object: Callback<Map<String, Any>> {

                override fun onResponse(
                    call: Call<Map<String, Any>>?,
                    response: Response<Map<String, Any>>?
                ) {

                    runBlocking {

                        if (response?.body()?.entries != null) {

                            repository.parsingMeasures(response.body()?.entries!!)

                            val measureList = repository.getMeasures()

                            measuresMutable.postValue(measureList)
                        } else {

                            messageIntMutable.postValue(R.string.list_empty_error)
                        }
                    }
                }

                override fun onFailure(call: Call<Map<String, Any>>?, t: Throwable?) {

                    messageIntMutable.postValue(R.string.error_service)
                }
            })
        }
    }

    // endregion

    // region search measure

    fun searchMeasureByCode(code: String) {

        GlobalScope.launch(Dispatchers.IO) {

            val measuresSearched = repository.getMeasuresByCode(code)

            measuresMutable.postValue(measuresSearched)
        }
    }

    // endregion

    // region reset list

    fun resetList() {

        GlobalScope.launch(Dispatchers.IO) {

            val measuresSaved = repository.getMeasures()

            measuresMutable.postValue(measuresSaved)
        }
    }

    // endregion
}