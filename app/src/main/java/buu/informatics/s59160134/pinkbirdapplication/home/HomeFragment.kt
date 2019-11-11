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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class HomeFragment : Fragment() {


    private var home : Home = Home("","","","")
    private var longPeriod = 28
    private var havePeriod = false

    @RequiresApi(value = Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)





        val currentDate = LocalDate.now()
        val lastDate = LocalDate.of(2019,11,9)
        var countDate = ChronoUnit.DAYS.between(lastDate,currentDate)
        checkStatusPeriod(countDate)
        home.currentDate = changeFormattedDate(LocalDate.now())
        binding.home = home



        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeFormattedDate(date : LocalDate) : String{
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        var formattedDate = date.format(formatter).toString()
        return  formattedDate
    }

    fun checkStatusPeriod(countDate : Long){
        if(havePeriod == true){
            home.statusPeriod = "ประจำเดือนวันที่"
            home.countDate = (countDate + 1).toString()
        }else if (countDate > longPeriod) {
            home.statusPeriod = "ประจำเกินมา"
            home.countDate = (countDate - longPeriod).toString()
        }else{
            home.statusPeriod = "ประจำเดือนในอีก"
            home.countDate = (longPeriod - countDate).toString()
        }
    }


}
