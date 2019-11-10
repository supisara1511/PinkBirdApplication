package buu.informatics.s59160134.pinkbirdapplication.home

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import buu.informatics.s59160134.pinkbirdapplication.database.Period
import buu.informatics.s59160134.pinkbirdapplication.database.PeriodDatabaseDao
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.LocalDateTime


class HomeViewModel(val database: PeriodDatabaseDao, application: Application) : AndroidViewModel(application) {



    private var viewModelJob = Job()
    private var period = MutableLiveData<Period?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        Log.i("MainViewModel", "MainViewModel created!")
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            period.value = getToPeriodFromDatabase()
        }
    }


    val startButtonVisible = Transformations.map(period) {
        null == it
    }


    val stopButtonVisible = Transformations.map(period) {
        null != it
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun startPeriod(){
        uiScope.launch {
            val newPeriod = Period()
            newPeriod.periodStart = LocalDate.now().toString()
            insert(newPeriod)
            period.value = getToPeriodFromDatabase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun endPeriod(){
        uiScope.launch {
            val oldPeriod = period.value ?: return@launch
            oldPeriod.periodEnd = LocalDate.now().toString()
            update(oldPeriod)
        }
    }


    private suspend fun getToPeriodFromDatabase(): Period? {
        return withContext(Dispatchers.IO) {
            var period = database.getToPeriod()
            period
        }
    }


    private suspend fun insert(period: Period) {
        withContext(Dispatchers.IO) {
            database.insert(period)
        }
    }

    private suspend fun update(night: Period) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

}