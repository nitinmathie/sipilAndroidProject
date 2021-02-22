package com.example.hopelastrestart1.ui.planEngineer.Task.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.FragmentPlannerDashboardBinding
import com.example.hopelastrestart1.databinding.FragmentSeDashboardBinding
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PlannerDashboardFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: FragmentPlannerDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_planner_dashboard, container, false)
        var binding: FragmentPlannerDashboardBinding? = DataBindingUtil.inflate(
            inflater, R.layout.fragment_planner_dashboard, container, false
        )
        //  binding = DataBindingUtil.setContentView(this, R.layout.fragment_se_dashboard)
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel::class.java)

        // Inflate the layout for this fragment
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val plan_nam = requireActivity().intent.getStringExtra("plan_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")
        val plan_name = requireActivity().intent.getStringExtra("plan_name")
        val peDashboard by lazyDeferred {
            viewModel.tasks1(username, organization_name, project_name, plan_name)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = peDashboard.await()
            orgs.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                binding?.editActivitiesCount?.setText("")
                /*binding.editDprSubmittedCount.setText("")
                binding.editDprsApproved.setText(it.ccbreaking_activity_name)
                binding.editActivitiesCompleted.setText(it.ccbreaking_activity_name)
                binding.editPendingActivities.setText(it.ccbreaking_activity_name)
                */
            })
        }

        return rootView
    }
}

