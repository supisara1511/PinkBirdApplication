package buu.informatics.s59160134.pinkbirdapplication.home

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import buu.informatics.s59160134.pinkbirdapplication.database.*
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel(val database: PeriodDatabaseDao, val dataStarted : StartedDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private var period = MutableLiveData<Period?>()
    private var started = MutableLiveData<Started?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)






    private val _currentDate = MutableLiveData<String>()
    val currentDate : LiveData<String>
        get() = _currentDate



    private val _havePeriod = MutableLiveData<Boolean>()
    val havePeriod : LiveData<Boolean>
        get() = _havePeriod

    private val _statusPeriod = MutableLiveData<String>()
    val statusPeriod : LiveData<String>
        get() = _statusPeriod

    private val _countDate = MutableLiveData<Int>()
    val countDate : LiveData<Int>
        get() = _countDate






    init {
        Log.i("HomeViewModel", "HomeViewModel created!")
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            period.value = getToPeriodFromDatabase()
            started.value = getToStartedFromDatabase()
            _currentDate.value = getDateFormat()
            var lastDate = changeDate(started.value!!.lastDate)
            _countDate.value = ChronoUnit.DAYS.between(lastDate,LocalDate.now()).toInt()
            Log.i("lastDate","${started.value}")
            Log.i("countDate","${_countDate.value}")

            _havePeriod.value = period.value != null
            checkStatusPeriod()
        }
    }


    fun changeDate(string: String) : LocalDate{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.ENGLISH)
        val date = LocalDate.parse(string, formatter)
        return date
    }


    fun checkStatusPeriod(){
        if(_havePeriod.value == true){
            _statusPeriod.value = "ประจำเดือนวันที่"
            _countDate.value = _countDate.value?.plus(1)
        }else if ( _countDate.value!! > started.value!!.longCycle) {
            _statusPeriod.value = "ประจำเกินมา"
            _countDate.value = _countDate.value?.minus(started.value!!.longCycle)
        }else{
            _statusPeriod.value = "ประจำเดือนในอีก"
            _countDate.value = (started.value!!.longCycle - _countDate.value!!)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateFormat() : String {
        var date = LocalDate.now()
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        var formattedDate = date.format(formatter).toString()
        Log.i("formattedDate","${formattedDate}")
        return  formattedDate
    }

    val startButtonVisible = Transformations.map(period) {
        null == it
    }


    val stopButtonVisible = Transformations.map(period) {
        null != it
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startPeriod(){
        uiScope.launch {
            _havePeriod.value = true
            Log.i("HavePeriod","${_havePeriod.value}")
            val newPeriod = Period()
            newPeriod.periodStart = LocalDate.now().toString()
            insert(newPeriod)
            started.value!!.lastDate = LocalDate.now().toString()
            var oldStarted = getToStartedFromDatabase()
            if (oldStarted != null) {
                oldStarted.lastDate = LocalDate.now().toString()
                update(oldStarted)
            }
            period.value = getToPeriodFromDatabase()
            initializeTonight()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun endPeriod(){
        uiScope.launch {
            _havePeriod.value = false
            Log.i("HavePeriod","${_havePeriod.value}")
            val oldPeriod = period.value ?: return@launch
            oldPeriod.periodEnd = LocalDate.now().toString()
            update(oldPeriod)
            initializeTonight()
        }
    }




    private suspend fun getToPeriodFromDatabase(): Period? {
        return withContext(Dispatchers.IO) {
            var period = database.getToPeriod()
            if(period?.periodEnd != ""){
                period = null
            }
            period
        }


    }

    private suspend fun getToStartedFromDatabase(): Started? {
        return withContext(Dispatchers.IO) {
            var started = dataStarted.getToStarted()
            started
        }


    }




    private suspend fun insert(period: Period) {
        withContext(Dispatchers.IO) {
            database.insert(period)
        }
    }

    private suspend fun update(period: Period) {
        withContext(Dispatchers.IO) {
            database.update(period)
        }

    }

    private suspend fun update(started: Started) {
        withContext(Dispatchers.IO) {
            dataStarted.update(started)
        }

    }

}