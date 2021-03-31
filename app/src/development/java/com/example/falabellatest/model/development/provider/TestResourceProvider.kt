package com.example.falabellatest.model.development.provider

import androidx.annotation.VisibleForTesting
import com.example.falabellatest.extensions.readTextStream
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.FileNotFoundException
import java.io.InputStream

@VisibleForTesting
object TestResourceProvider {

    fun getMapObject(path: String): Map<String, Any> {

        val stringJson: String = getStringFromResources(path)

        val map: Map<String, Any> = HashMap()

        return Gson().fromJson(stringJson, map.javaClass)
    }

    fun getStringFromResources(path: String): String {

        return this.getResourceAsStream(path).readTextStream
    }

    fun getResourceAsStream(path: String): InputStream {

        return try {

            this.javaClass.getResourceAsStream("/$path")!!
        } catch (ex: Exception) {

            throw FileNotFoundException("make sure the required file is on $path")
        }
    }
}