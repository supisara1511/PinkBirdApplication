package buu.informatics.s59160134.pinkbirdapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "period_history_table")
data class Period(
    @PrimaryKey(autoGenerate = true)
    var periodID: Long = 0L,

    @ColumnInfo(name = "start_date_period")
    val startDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_date_period")
    var endDate: Long = startDate

    )

