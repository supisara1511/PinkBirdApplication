package buu.informatics.s59160134.pinkbirdapplication.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160134.pinkbirdapplication.database.Period
import buu.informatics.s59160134.pinkbirdapplication.database.PeriodDatabaseDao
import kotlinx.coroutines.*


class HistoryViewModel(val database: PeriodDatabaseDao, application: Application) : AndroidViewModel(application) {




    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var period = MutableLiveData<Period?>()
    val periods = database.getAllPeriod()

    init {
        Log.i("HistoryViewModel", "MainViewModel created!")
        initializeGameScore()
    }


    private fun initializeGameScore() {
        uiScope.launch {
            period.value = getGameScoreFromDatabase()
        }
    }

    private suspend fun getGameScoreFromDatabase(): Period? {
        return withContext(Dispatchers.IO) {
            var period = database.getToPeriod()
            period
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }







}