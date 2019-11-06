package buu.informatics.s59160134.pinkbirdapplication.setting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160134.pinkbirdapplication.R
import buu.informatics.s59160134.pinkbirdapplication.database.Setting
import buu.informatics.s59160134.pinkbirdapplication.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    private var setting : Setting = Setting()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting,container,false)
        binding.settingApp = setting
        return binding.root
    }


}
