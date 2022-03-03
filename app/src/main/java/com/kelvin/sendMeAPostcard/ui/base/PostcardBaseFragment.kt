package com.kelvin.sendMeAPostcard.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class PostcardBaseFragment: Fragment() {
    fun findSafeNavController(): NavController {
        return this.findNavController()
    }
}