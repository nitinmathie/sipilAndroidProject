package com.example.hopelastrestart1.ui.siteEngineer.tabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener_MyDpr
import com.example.hopelastrestart1.adapter.MyDprsRvAdapter
import com.example.hopelastrestart1.data.db.entities.DprInfo
import com.example.hopelastrestart1.ui.planEngineer.Task.ActivitiesActivity
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModel
import com.example.hopelastrestart1.ui.planEngineer.Task.tabs.TaskViewModelFactory
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SubmittedDprsFragment : Fragment(), KodeinAware, CellClickListener_MyDpr {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_submitted_dprs, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_assigned_to_me)
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")
        val plan_name = requireActivity().intent.getStringExtra("plan_name")
        val context = rootView.getContext();
        val tasks by lazyDeferred {
            viewModel.dprssubmittedbyme(username, organization_name, project_name)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = tasks.await()
            orgs.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter = MyDprsRvAdapter(
                    it,
                    context,
                    this,
                    username,
                    organization_name,
                    project_name
                )
            })
        }
///
        return rootView
    }

    override fun onCellClickListener(
        activity: DprInfo,
        username: String,
        organization_name: String,
        project_name: String,
        assign: Int
    ) {
        //if(report_type=="Material") Display that screen Todo

        val intent =  Intent(getActivity(), ActivitiesActivity::class.java)
        startActivity(intent)
    }


}