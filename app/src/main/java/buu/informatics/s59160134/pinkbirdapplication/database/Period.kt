package buu.informatics.s59160134.pinkbirdapplication.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "period_history_table")
data class Period @RequiresApi(Build.VERSION_CODES.O)
constructor(
    @PrimaryKey(autoGenerate = true)
    var periodId: Long = 0L,

    @ColumnInfo(name = "period_start")
    var periodStart: String = "",


    @ColumnInfo(name = "period_end")
    var periodEnd: String = ""
)
