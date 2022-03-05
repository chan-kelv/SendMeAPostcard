package com.kelvin.postcardz.ui.leftTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kelvin.postcardz.databinding.FragmentSimpleBinding

class LeftSecondFragment : Fragment() {
    // don't use this one - only for setup
    private var _leftSecondBinding: FragmentSimpleBinding? = null
    // only use this between onCreateView and onDestroyView
    private val leftSecondBinding get() = _leftSecondBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _leftSecondBinding = FragmentSimpleBinding.inflate(inflater, container, false)
        val view = leftSecondBinding.root
        leftSecondBinding.lablelId.text = this.javaClass.canonicalName
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(): LeftSecondFragment {
            return LeftSecondFragment()
        }
    }
}