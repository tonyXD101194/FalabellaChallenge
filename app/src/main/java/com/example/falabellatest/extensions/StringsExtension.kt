package com.example.falabellatest.extensions

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

fun Context.format(id: Int, string: String): String {

    return String.format(
        this.resources.getString(id),
        string
    )
}

val InputStream.readTextStream: String
    get() {

        val stringBuilder = StringBuilder()

        val inputStreamReader: InputStreamReader = this.reader(Charsets.UTF_8)

        val bufferedReader = BufferedReader(inputStreamReader)

        var line: String? = bufferedReader.readLine()

        while (line != null) {

            stringBuilder.append(line)

            line = bufferedReader.readLine()
        }

        bufferedReader.close()

        return stringBuilder.toString()
    }