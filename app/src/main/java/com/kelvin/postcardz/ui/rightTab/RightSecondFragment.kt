package com.kelvin.postcardz.ui.rightTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kelvin.postcardz.databinding.FragmentSimpleBinding

class RightSecondFragment : Fragment() {
    // don't use this one - only for setup
    private var _rightSecondBinding: FragmentSimpleBinding? = null
    // only use this between onCreateView and onDestroyView
    private val rightSecondBinding get() = _rightSecondBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _rightSecondBinding = FragmentSimpleBinding.inflate(inflater, container, false)
        val view = rightSecondBinding.root
        rightSecondBinding.lablelId.text = this.javaClass.canonicalName
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(): RightSecondFragment {
            return RightSecondFragment()
        }
    }
}