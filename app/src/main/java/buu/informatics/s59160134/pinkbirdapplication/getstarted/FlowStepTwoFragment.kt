package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentFlowStepTwoBinding
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentGetstartedBinding

/**
 * A simple [Fragment] subclass.
 */
class FlowStepTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepTwoBinding>(inflater,R.layout.fragment_flow_step_two,container,false)
        val numberPicker = binding.longPeriodPicker
        if (numberPicker != null) {
            val values = arrayOf("22 Days", "23 Days", "24 Days", "25 Days", "26 Days", "27 Days", "28 Days")
            numberPicker.minValue = 0
            numberPicker.maxValue = values.size - 1
            numberPicker.displayedValues = values
            numberPicker.wrapSelectorWheel = true
        }
        binding.nextButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(R.id.action_flow_step_two_to_flow_step_three)
        }

        return binding.root
    }


}
