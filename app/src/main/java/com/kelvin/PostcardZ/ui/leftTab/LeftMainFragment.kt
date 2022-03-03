package com.kelvin.PostcardZ.ui.leftTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kelvin.PostcardZ.R
import com.kelvin.PostcardZ.databinding.FragmentSimpleBinding

class LeftMainFragment : Fragment() {
    // don't use this one - only for setup
    private var _leftMainBinding: FragmentSimpleBinding? = null
    // only use this between onCreateView and onDestroyView
    private val leftMainBinding get() = _leftMainBinding!!

    private lateinit var leftViewModel: LeftMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view
        _leftMainBinding = FragmentSimpleBinding.inflate(inflater, container, false)
        val view = leftMainBinding.root

        // create the VM
        leftViewModel =
            ViewModelProvider(this).get(LeftMainViewModel::class.java)

        // Navigate to next fragment
        leftMainBinding.lablelId.setOnClickListener {
            findNavController().navigate(R.id.action_leftMainFragment_to_leftTabSecondFragment)
        }

        // How to observe text change
        leftViewModel.label.observe(viewLifecycleOwner, Observer {
            leftMainBinding.lablelId.text = it
        })
        // How to make changes to VM fields
        this.javaClass.canonicalName?.let { leftViewModel.setLabelId("Start Here!") }

        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(): LeftMainFragment {
            return LeftMainFragment()
        }
    }
}