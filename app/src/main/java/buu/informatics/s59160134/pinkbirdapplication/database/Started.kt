package buu.informatics.s59160134.pinkbirdapplication.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "period_start_table")
data class Started
constructor(
    @PrimaryKey(autoGenerate = true)
    var periodStartId: Long = 0L,

    @ColumnInfo(name = "long_period")
    var longPeriod: Int = 4,

    @ColumnInfo(name = "long_cycle")
    var longCycle: Int = 4,

    @ColumnInfo(name = "last_ate")
    var lastDate: String = ""
)
