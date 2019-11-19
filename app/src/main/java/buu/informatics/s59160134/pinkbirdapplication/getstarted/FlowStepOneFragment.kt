package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentFlowStepOneBinding
import java.util.logging.LogRecord
import java.util.logging.LoggingPermission

/**
 * A simple [Fragment] subclass.
 */
class FlowStepOneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepOneBinding>(inflater,R.layout.fragment_flow_step_one,container,false)
        var numberPicker = binding.longPeriodPicker
            numberPicker.minValue = 1
            numberPicker.maxValue = 10
            numberPicker.value = 4
            numberPicker.wrapSelectorWheel = true




        binding.nextButton.setOnClickListener{ view : View ->
        Log.i("numberPicker","${numberPicker.value}")
            view.findNavController().navigate(FlowStepOneFragmentDirections.actionFlowStepOneToFlowStepTwo(numberPicker.value))
        }
        return binding.root
    }

}
