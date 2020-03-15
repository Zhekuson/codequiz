package com.nuclearfoxes.codequiz.ui.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TestsViewModel:ViewModel() {
    /**
     * list of chosen tags
     */
    lateinit var chosenTagsList: LiveData<ArrayList<String>>

    lateinit var allAvailableTagsList: ArrayList<String>


}