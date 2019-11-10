package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.MainActivity

import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentGetstartedBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class GetStartedFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGetstartedBinding>(inflater,R.layout.fragment_getstarted,container,false)


        binding.nextButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(R.id.action_get_started_to_flow_step_one)
        }
        return binding.root
    }
}
