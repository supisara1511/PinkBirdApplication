package buu.informatics.s59160134.pinkbirdapplication.getstarted


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isGone
import androidx.navigation.Navigation
import buu.informatics.s59160134.pinkbirdapplication.MainActivity

import buu.informatics.s59160134.pinkbirdapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A simple [Fragment] subclass.
 */
class GetStartedFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_getstarted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.navigate_action_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.next_action, null)
        )
    }


}
