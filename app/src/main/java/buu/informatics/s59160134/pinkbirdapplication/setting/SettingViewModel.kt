package buu.informatics.s59160134.pinkbirdapplication.setting

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import buu.informatics.s59160134.pinkbirdapplication.database.Started
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabaseDao
import kotlinx.coroutines.*

class SettingViewModel(val database: StartedDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private var started = MutableLiveData<Started?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)



    private val _longPeriod = MutableLiveData<String>()
    val longPeriod : LiveData<String>
        get() = _longPeriod



    private val _longCycle = MutableLiveData<String>()
    val longCycle : LiveData<String>
        get() = _longCycle

    private val _getStarted = MutableLiveData<Boolean>()
    val getStarted : LiveData<Boolean>
        get() = _getStarted


    private fun initializeTonight() {
        uiScope.launch {
            started.value = getToStartedFromDatabase()
            _longPeriod.value = started.value?.longPeriod.toString()
            _longCycle.value = started.value?.longCycle.toString()
            Log.i("SettingViewModel","${_longCycle.value} ${_longPeriod.value}")
        }
    }

    init {
        Log.i("SettingViewModel", "SettingViewModel created!")
        initializeTonight()
        _getStarted.value = false


    }

    fun getStarted(){
        _getStarted.value = true
    }


    fun saveStarted(){
        uiScope.launch {
            var oldStarted = started.value?.periodStartId?.let { getToStarted(it) }
            if (oldStarted != null) {
                oldStarted.longCycle = _longCycle.value!!.toInt()
                oldStarted.longPeriod = _longPeriod.value!!.toInt()
                update(oldStarted)
            }
            initializeTonight()
        }
    }



    private suspend fun getToStarted(long: Long): Started? {
        return withContext(Dispatchers.IO) {
            var started = database.get(long)
            started
        }
    }

    private suspend fun getToStartedFromDatabase(): Started? {
        return withContext(Dispatchers.IO) {
            var started = database.getToStarted()
            started
        }
    }

    private suspend fun update(started: Started) {
        withContext(Dispatchers.IO) {
            database.update(started)
        }

    }
}