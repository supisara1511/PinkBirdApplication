package buu.informatics.s59160134.pinkbirdapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeriodDatabaseDao {
    @Insert
    fun insert(period: Period)

    @Update
    fun update(period: Period)

    @Query("SELECT * from period_history_table WHERE periodID = :key")
    fun get(key: Long): Period

    @Query("DELETE FROM period_history_table")
    fun clear()


    @Query("SELECT * FROM period_history_table ORDER BY periodID DESC")
    fun getAllNights(): LiveData<List<Period>>

    @Query("SELECT * FROM period_history_table ORDER BY periodID DESC LIMIT 1")
    fun getTonight(): Period?
}