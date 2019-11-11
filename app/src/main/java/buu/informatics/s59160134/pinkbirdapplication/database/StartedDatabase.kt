package buu.informatics.s59160134.pinkbirdapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Started::class], version = 1, exportSchema = false)
abstract class StartedDatabase : RoomDatabase()  {
    abstract val StartedDatabaseDao : StartedDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: StartedDatabase? = null

        fun getInstance(context: Context): StartedDatabase {
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StartedDatabase::class.java,
                        "period_start_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}