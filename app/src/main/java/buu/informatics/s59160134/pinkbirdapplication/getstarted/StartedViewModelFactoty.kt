package buu.informatics.s59160134.pinkbirdapplication.getstarted

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabaseDao

class StartedViewModelFactoty (
    private val dataSource: StartedDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartedViewModel::class.java)) {
            return StartedViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}