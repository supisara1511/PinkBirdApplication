package buu.informatics.s59160134.pinkbirdapplication.home


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.Home
import buu.informatics.s59160134.pinkbirdapplication.database.PeriodDatabase
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabase
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentHomeBinding
import buu.informatics.s59160134.pinkbirdapplication.history.HistoryViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var home : Home = Home("","","","")
    private var longPeriod = 28
    private var havePeriod = false

    @RequiresApi(value = Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = PeriodDatabase.getInstance(application).PeriodDatabaseDao
        val dataStarted = StartedDatabase.getInstance(application).StartedDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, dataStarted ,application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }



}
