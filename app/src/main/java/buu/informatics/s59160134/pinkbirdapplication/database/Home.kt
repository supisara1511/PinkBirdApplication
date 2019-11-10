package buu.informatics.s59160134.pinkbirdapplication.database

import androidx.room.Entity
import java.util.*


@Entity(tableName = "home_table")
data class Home(
    var currentDate : String = "",var lastDate : String = "",var statusPeriod : String = "",var countDate: String )  {
}


