package buu.informatics.s59160134.pinkbirdapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "period_history_table")
data class Period (
    @PrimaryKey(autoGenerate = true)
    var periodId: Long = 0L,

    @ColumnInfo(name = "period_start")
    var periodStart: String = "",


    @ColumnInfo(name = "period_end")
    var periodEnd: String = ""
)
