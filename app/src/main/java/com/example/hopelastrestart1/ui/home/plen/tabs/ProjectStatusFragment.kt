package com.example.hopelastrestart1.ui.home.plen.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hopelastrestart1.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class ProjectStatusFragment: Fragment(), KodeinAware {
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_project_status, container, false)
        val graph = rootView.findViewById(R.id.graph) as GraphView
        val series = LineGraphSeries(
            arrayOf<DataPoint>(
                DataPoint(0.toDouble(), 1.toDouble()),
                DataPoint(1.toDouble(), 5.toDouble()),
                DataPoint(2.toDouble(), 3.toDouble()),
                DataPoint(3.toDouble(), 2.toDouble()),
                DataPoint(4.toDouble(), 6.toDouble())
            )
        )
        graph.addSeries(series)
        return rootView

    }
}