package buu.informatics.s59160134.pinkbirdapplication.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160134.pinkbirdapplication.database.PeriodDatabaseDao
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabaseDao


class HomeViewModelFactory (
    private val dataSource: PeriodDatabaseDao,
    private val dataStarted: StartedDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource,dataStarted, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}