package buu.informatics.s59160134.pinkbirdapplication.getstarted

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160134.pinkbirdapplication.database.Period
import buu.informatics.s59160134.pinkbirdapplication.database.Started
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabaseDao
import kotlinx.coroutines.*

class StartedViewModel(val database: StartedDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private var started = MutableLiveData<Started?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var longPeriod : Int = 0
    var longCycle : Int = 0
    var lastDate : String = ""

    private var _startedFinishEvent = MutableLiveData<Boolean?>()
    val startedFinishEvent: LiveData<Boolean?>
        get() = _startedFinishEvent


    init {
        Log.i("StartedViewModel", "StartedViewModel created!")
        started.value = null
        _startedFinishEvent.value = false

    }




    fun setStrated(longPeriod : Int,longCycle : Int, lastDate : String){
        this.longPeriod = longPeriod
        this.longCycle = longCycle
        this.lastDate = lastDate
    }

    fun startStarted(){
        uiScope.launch {
            Log.i("StartedViewModel","startStarted")
            clear()
            val newStarted = Started()
            newStarted.longPeriod = longPeriod
            newStarted.longCycle = longCycle
            newStarted.lastDate = lastDate
            insert(newStarted)
            _startedFinishEvent.value = true
        }
    }

    private suspend fun insert(started: Started) {
        withContext(Dispatchers.IO) {
            database.insert(started)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }




}