package buu.informatics.s59160134.pinkbirdapplication.history


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.PeriodDatabase
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentHistoryBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,
            R.layout.fragment_history,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PeriodDatabase.getInstance(application).PeriodDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        val adapter = HistoryAdapter()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)
        binding.historyViewModel = viewModel
        binding.lifecycleOwner = this

        binding.historyList.adapter = adapter
        binding.setLifecycleOwner(this)

        viewModel.periods.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }
}
