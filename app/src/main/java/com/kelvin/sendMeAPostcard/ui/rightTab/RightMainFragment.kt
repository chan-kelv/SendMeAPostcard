package com.kelvin.sendMeAPostcard.ui.rightTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kelvin.sendMeAPostcard.R
import com.kelvin.sendMeAPostcard.databinding.FragmentSimpleBinding

class RightMainFragment: Fragment() {
    // don't use this one - only for setup
    private var _rightMainBinding: FragmentSimpleBinding? = null
    // only use this between onCreateView and onDestroyView
    private val rightMainBinding get() = _rightMainBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _rightMainBinding = FragmentSimpleBinding.inflate(inflater, container, false)
        val view = rightMainBinding.root

        rightMainBinding.lablelId.setOnClickListener {
            findNavController().navigate(R.id.action_rightMainFragment_to_rightSecondaryFragment)
        }

        rightMainBinding.lablelId.text = this.javaClass.canonicalName
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(): RightMainFragment {
            return RightMainFragment()
        }
    }
}