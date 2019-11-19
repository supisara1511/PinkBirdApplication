package buu.informatics.s59160134.pinkbirdapplication.setting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.R.id.home_dest
import buu.informatics.s59160134.pinkbirdapplication.database.Setting
import buu.informatics.s59160134.pinkbirdapplication.database.StartedDatabase
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentSettingBinding
import buu.informatics.s59160134.pinkbirdapplication.getstarted.StartedViewModel
import buu.informatics.s59160134.pinkbirdapplication.getstarted.StartedViewModelFactoty


class SettingFragment : Fragment() {
    private lateinit var viewModel: SettingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = StartedDatabase.getInstance(application).StartedDatabaseDao
        val viewModelFactory = SettingViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingViewModel::class.java)
        binding.settingViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getStarted.observe(this, Observer<Boolean?> { hasFinished ->
            if (hasFinished!!)
                onFinished()
        })

        return binding.root
    }

    private fun onFinished() {
        view?.findNavController()!!.navigate(R.id.getStartedFragment)

    }
}
