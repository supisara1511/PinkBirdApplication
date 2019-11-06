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

import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.Home
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentHomeBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    private val home : Home = Home()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)
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


}
