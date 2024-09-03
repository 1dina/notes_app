package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [Note::class],
    version = 2,
)
abstract class RoomDBHelper : RoomDatabase() {
    abstract val dao: NoteDao

    companion object {
        private var INSTANCE: RoomDBHelper? = null

        fun getInstance(context: Context): RoomDBHelper {
            val MIGRATION_1_2: Migration = object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("ALTER TABLE note "
                            + " ADD COLUMN title TEXT")
                }
            }
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RoomDBHelper::class.java, "MyDB"
                ).addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}