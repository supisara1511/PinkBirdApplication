package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentFlowStepThreeBinding

/**
 * A simple [Fragment] subclass.
 */
class FlowStepThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFlowStepThreeBinding>(inflater,R.layout.fragment_flow_step_three,container,false)
        binding.nextButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(R.id.action_flow_step_three_to_home)
        }

        return  binding.root
    }
}
