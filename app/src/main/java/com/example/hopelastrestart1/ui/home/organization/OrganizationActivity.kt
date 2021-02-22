package com.example.hopelastrestart1.ui.home.organization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener
import com.example.hopelastrestart1.adapter.OrganizationAdapter
import com.example.hopelastrestart1.data.db.entities.Organization
import com.example.hopelastrestart1.databinding.ActivityAddOrganizationBinding
import com.example.hopelastrestart1.ui.home.project.ProjectActivity
import com.example.hopelastrestart1.util.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class OrganizationActivity : AppCompatActivity(), KodeinAware, CellClickListener {
    override val kodein by kodein()
    private val factory: OrganizationViewModelFactory by instance()
    private lateinit var viewModel: OrganizationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization)
        setSupportActionBar(toolbar1)
        var recycleview = findViewById<RecyclerView>(R.id.recyclerview)
        viewModel = ViewModelProviders.of(this, factory).get(OrganizationViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        recycleview.layoutManager = linearLayoutManager
        val username=intent.getStringExtra("username")
        val organizations by lazyDeferred {
            viewModel.organizations1(username)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = organizations.await()
            orgs.observe(this, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter =OrganizationAdapter(it, this, username)
               // initRecyclerView(it.toOrganizationItem())
            })
        }
/*
        val organizations by lazyDeferred {
                    viewModel.organizations1(username)
                }
                Coroutines.main {
                    progress_bar.show()
                    val orgs = organizations.await()
                    orgs.observe(this, Observer {
                       progress_bar.hide()
                        it.size.toString()
                        initRecyclerView(it.toOrganizationItem())
                    })
                }
*/
        val add = findViewById<FloatingActionButton>(R.id.fab)
        add.setOnClickListener {
            val intent =Intent(this, AddOrganizationActivity::class.java)
            intent.putExtra("username", username)

                startActivity(intent)
        }

    }
    private fun initRecyclerView(organizationItem: List<OrganizationItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply{
            addAll(organizationItem)
        }
        recyclerview.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view,  ->
            //item.getItem(se)


            val intent =  Intent(this, ProjectActivity::class.java)
        }
    }
    private fun List<Organization>.toOrganizationItem() : List<OrganizationItem>{
        return this.map{
            OrganizationItem(it)
        }
    }

    override fun onCellClickListener(organization: Organization, username:String) {
        val intent =  Intent(this, ProjectActivity::class.java)
        intent.putExtra("organization_name", organization.organization_name)
        intent.putExtra("organization_id", organization.organization_id)
      //  val username=intent.getStringExtra("username")
        intent.putExtra("username", username)
        startActivity(intent)
    }
}