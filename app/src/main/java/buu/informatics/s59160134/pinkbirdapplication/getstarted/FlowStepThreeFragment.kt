package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabase
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabaseDao

import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentFlowStepThreeBinding
import java.time.LocalDate


/**
 * A simple [Fragment] subclass.
 */
class FlowStepThreeFragment : Fragment() {
    private lateinit var viewModel: StartedViewModel
    private var lastDate : String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepThreeBinding>(inflater, R.layout.fragment_flow_step_three,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = StartedDatabase.getInstance(application).StartedDatabaseDao
        val viewModelFactory = StartedViewModelFactoty(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StartedViewModel::class.java)

        binding.startedViewModel = viewModel
        binding.lifecycleOwner = this
        val args = FlowStepThreeFragmentArgs.fromBundle(arguments!!)
        val calendarView = binding.lastPeriod
        lastDate = LocalDate.now().toString()
        viewModel.setStrated(args.longPeriod,args.longCycle,lastDate)



        viewModel.startedFinishEvent.observe(this, Observer<Boolean?> { hasFinished ->
            if (hasFinished!!)
                onFinished()
        })



        calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            lastDate = "" + year + "-" + (month + 1) + "-" + dayOfMonth
            viewModel.setStrated(args.longPeriod,args.longCycle,lastDate)
        }





        return  binding.root
    }

    private fun onFinished() {
        view?.findNavController()!!.navigate(R.id.home_dest)

    }


}




