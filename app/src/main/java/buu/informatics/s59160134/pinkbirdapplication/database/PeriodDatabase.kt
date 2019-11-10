package buu.informatics.s59160134.pinkbirdapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Period::class], version = 1, exportSchema = false)
abstract class PeriodDatabase : RoomDatabase()  {
    abstract val PeriodDatabaseDao: PeriodDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PeriodDatabase? = null

        fun getInstance(context: Context): PeriodDatabase {
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PeriodDatabase::class.java,
                        "period_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}