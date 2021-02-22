package com.example.hopelastrestart1.ui.home.plen.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hopelastrestart1.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class TestFragment: Fragment(), KodeinAware {
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_project_status, container, false)

        return rootView

    }
}