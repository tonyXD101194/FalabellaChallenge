package com.example.falabellatest.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.falabellatest.database.FalabellaDataProvider.Companion.DATABASE_FALABELLA_NAME
import com.example.falabellatest.database.FalabellaDataProvider.Companion.DATABASE_FALABELLA_VERSION
import com.example.falabellatest.database.dao.MeasureDAO
import com.example.falabellatest.model.room.MeasureEntity
import java.util.concurrent.Executor

@Database(
    entities = [MeasureEntity::class],
    version = DATABASE_FALABELLA_VERSION
)
abstract class FalabellaRoomDatabase : RoomDatabase() {

    abstract fun measureDao(): MeasureDAO

    companion object {

        fun clearAllTables() {

            instance?.clearAllTables()
        }

        @Volatile
        private var instance: FalabellaRoomDatabase? = null

        val measureDao: MeasureDAO
            get() = instance!!.measureDao()

        fun getInstance(context: Context, inMemoryDatabase: Boolean = false, executor: Executor? = null): FalabellaRoomDatabase {

            return instance ?: if (inMemoryDatabase) {

                val builder: Builder<FalabellaRoomDatabase> = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    FalabellaRoomDatabase::class.java
                )

                if (executor != null) {

                    builder.setQueryExecutor(executor)
                    builder.setTransactionExecutor(executor)
                }

                builder.build()
                    .also {
                        instance = it
                    }
            } else {

                val builder: Builder<FalabellaRoomDatabase> = Room.databaseBuilder(
                    context.applicationContext,
                    FalabellaRoomDatabase::class.java,
                    DATABASE_FALABELLA_NAME
                )

                if (executor != null) {

                    builder.setQueryExecutor(executor)
                    builder.setTransactionExecutor(executor)
                }

                builder.build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}