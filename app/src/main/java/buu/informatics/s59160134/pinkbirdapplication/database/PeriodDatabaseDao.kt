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

    @Query("SELECT * from period_history_table WHERE periodId = :key")
    fun get(key: Long): Period?

    @Query("DELETE FROM period_history_table")
    fun clear()


    @Query("SELECT * FROM period_history_table ORDER BY periodId DESC LIMIT 1")
    fun getToPeriod(): Period?

    @Query("SELECT * FROM period_history_table ORDER BY periodId DESC")
    fun getAllPeriod(): LiveData<List<Period>>


}