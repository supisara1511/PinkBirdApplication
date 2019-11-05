package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentFlowStepOneBinding

/**
 * A simple [Fragment] subclass.
 */
class FlowStepOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepOneBinding>(inflater,R.layout.fragment_flow_step_one,container,false)
        val numberPicker = binding.longPeriodPicker
        if (numberPicker != null) {
            val values = arrayOf("1 Days", "2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "7 Days")
            numberPicker.minValue = 0
            numberPicker.maxValue = values.size - 1
            numberPicker.displayedValues = values
            numberPicker.wrapSelectorWheel = true
        }

        binding.nextButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(R.id.action_flow_step_one_to_flow_step_two)
        }
        return binding.root
    }

}
