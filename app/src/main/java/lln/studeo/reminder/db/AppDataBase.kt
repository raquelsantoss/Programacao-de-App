package lln.studeo.reminder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lln.studeo.reminder.entity.Descriptive

@Database(entities = [Descriptive::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun descriptiveDao(): DescriptiveDao

    companion object {
        private var sInstance: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDataBase::class.java, "example")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }
}
