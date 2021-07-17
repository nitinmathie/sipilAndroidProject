package com.example.hopelastrestart1.ui.home.plen.tabs

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListenerPlan
import com.example.hopelastrestart1.data.db.entities.Plan

import com.example.hopelastrestart1.ui.home.plen.PlanActivity
import com.example.hopelastrestart1.ui.home.plen.AddPlenActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PlanFragment : Fragment(), KodeinAware, CellClickListenerPlan {
    override val kodein by kodein()
    private val factory: PlanViewModelFactory by instance()
    private lateinit var viewModel: PlanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.plan_fragment, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_plan)
        viewModel = ViewModelProviders.of(this, factory).get(PlanViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager

        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")

      /*  val plans by lazyDeferred {
            viewModel.plans1(username, organization_name, project_name)
        }
        Coroutines.main {
            progress_bar.show()
            val plans = plans.await()
            plans.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter = PlanAdapter(it, this, username, organization_name, project_name)
                // initRecyclerView(it.toOrganizationItem())
            })
        }*/
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_plan)
        add.setOnClickListener {
            val intent = Intent(activity, AddPlenActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            intent.putExtra("project_name", project_name)

            startActivity(intent)
        }
        return rootView
    }

    private fun initRecyclerView(planItem: List<PlanItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(planItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view, ->
            //item.getItem(se)


            val intent = Intent(activity, PlanActivity::class.java)
        }
    }

    private fun List<Plan>.toPlanItem(): List<PlanItem> {
        return this.map {
            PlanItem(it)
        }
    }

   /* override fun onCellClickListener(plan: Plan, username: String,  organization_name: String, project_name: String) {
        val intent = Intent(context, TaskActivity::class.java)
        //intent.putExtra("plan_name", plan.plan_id)
        // intent.putExtra("organization_name", organization_name)
        //  val username=intent.getStringExtra("username")
        val plan_name = (plan.plan_id).toString()
        intent.putExtra("plan_name", plan_name)
        intent.putExtra("project_name", project_name)
        intent.putExtra("username", username)
        intent.putExtra("organization_name", organization_name)
        startActivity(intent)
    }
*/
    override fun onCellClickListenerPlan(
        plan: Plan,
        username: String,
        organization_name: String,
        project_name: String
    ) {
        TODO("Not yet implemented")
    }

}
