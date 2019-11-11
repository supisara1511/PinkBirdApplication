package buu.informatics.s59160134.pinkbirdapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StartedDatabaseDao {
    @Insert
    fun insert(started: Started)

    @Update
    fun update(started: Started)

    @Query("SELECT * from period_start_table WHERE periodStartId = :key")
    fun get(key: Long): Started?

    @Query("DELETE FROM period_start_table")
    fun clear()


    @Query("SELECT * FROM period_start_table ORDER BY periodStartId DESC LIMIT 1")
    fun getToStarted(): Started?

    @Query("SELECT * FROM period_start_table ORDER BY periodStartId DESC")
    fun getAllStarted(): LiveData<List<Started>>

}