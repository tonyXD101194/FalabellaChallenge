package com.example.falabellatest.api

import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import retrofit2.Response

class ApiServiceTest {

    @Mock
    private lateinit var service: ApiServiceInterface

    @Before
    fun setUp() {

        service = Mockito.mock(ApiServiceInterface::class.java, RETURNS_DEEP_STUBS)
    }

    @Test
    fun get_measures_result_success() {

        Mockito.`when`(service.getMeasures().execute()).thenReturn(
            Response.success(
                mapOf()
        ))

        val response = service.getMeasures().execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse != null)
    }

    @Test
    fun get_measures_result_error() {

        Mockito.`when`(service.getMeasures().execute()).thenReturn(Response.error(404,
            ResponseBody.create(null, "Something wrong append")))

        val response = service.getMeasures().execute()
        val bodyResponse = response.body()

        Assert.assertTrue(bodyResponse == null)
    }
}