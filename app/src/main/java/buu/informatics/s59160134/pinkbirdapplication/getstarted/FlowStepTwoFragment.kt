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

/**
 * A simple [Fragment] subclass.
 */
class FlowStepTwoFragment : Fragment() {
    var longCycel : Int = 28
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepTwoBinding>(inflater,R.layout.fragment_flow_step_two,container,false)
        val numberPicker = binding.longCycelPicker
            numberPicker.minValue = 1
            numberPicker.maxValue = 35
            numberPicker.value = longCycel
            numberPicker.wrapSelectorWheel = true
        val args = FlowStepTwoFragmentArgs.fromBundle(arguments!!)
        val longPeriod = args.longPeriod



        binding.nextButton.setOnClickListener{ view : View ->
            val saveArgs = numberPicker.value
            view.findNavController().navigate(FlowStepTwoFragmentDirections.actionFlowStepTwoToFlowStepThree(longPeriod,saveArgs))

        }

        return binding.root
    }


}
