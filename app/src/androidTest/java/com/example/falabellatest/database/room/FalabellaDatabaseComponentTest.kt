package com.example.falabellatest.database.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.falabellatest.database.FalabellaDatabaseTestRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FalabellaDatabaseComponentTest {

    @get:Rule
    val rule = FalabellaDatabaseTestRule()

    @Test
    fun getInstanceTest() {

        val instance: FalabellaRoomDatabase? = FalabellaRoomDatabase.getInstance(rule.context)

        val newInstance: FalabellaRoomDatabase? = FalabellaRoomDatabase.getInstance(rule.context)

        assertThat(instance).isNotNull()
        assertThat(newInstance).isNotNull()
        assertThat(instance).isEqualTo(newInstance)
    }
}