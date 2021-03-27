package com.example.hopelastrestart1.ui.home.project.tabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.GlobalData
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.CellClickListener2
import com.example.hopelastrestart1.adapter.StoreRVAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.Store
import com.example.hopelastrestart1.model.GetStores
import com.example.hopelastrestart1.ui.home.UpdateUserRoleActivity
import com.example.hopelastrestart1.ui.home.project.AddStoreActivity
import com.example.hopelastrestart1.util.*
import kotlinx.android.synthetic.main.activity_organization.*
import kotlinx.android.synthetic.main.fragment_stores.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import com.example.hopelastrestart1.viewmodel.StoreViewModel
import org.kodein.di.generic.instance

class StoresFragment : Fragment(), KodeinAware, CellClickListener2 {
    override val kodein by kodein()
    private val factory: StoreViewModelFactory by instance()
    private lateinit var viewModel: StoreViewModel
    private lateinit var recycleview: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_stores, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // ViewModelProviders().get(ProjectViewModel)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(StoreViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")


        val getStoreData = GetStores(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!, organization_name, ""
        )
        getStores(getStoreData)
        rootView.fab_add_store.setOnClickListener {
            val intent = Intent(activity, AddStoreActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            startActivity(intent)
        }
        /*   val stores by lazyDeferred {
               viewModel.stores1(username, organization_name)
           }
           Coroutines.main {
               progress_bar.show()
               val orgs = stores.await()
               orgs.observe(viewLifecycleOwner, Observer {
                   progress_bar.hide()
                   it.size.toString()
                   recycleview.adapter = StoreRVAdapter(it,this , username)
               })
           }*/
        return rootView
    }

    override fun onCellClickListener(store: Store, username: String) {
        val intent = Intent(activity, UpdateUserRoleActivity::class.java)
        intent.putExtra("store_name", store.store_name)
        intent.putExtra("store_name", store.store_name)
        val username = intent.getStringExtra("username")
        intent.putExtra("username", username)
        startActivity(intent)
    }

    private fun getStores(getStores: GetStores) {
        viewModel.getStores(getStores).observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { projects -> retrieveList(projects.body()?.stores!!) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })


    }

    private fun retrieveList(stores: List<Store>) {
        if (stores.size > 0) {
            recycleview.adapter =
                StoreRVAdapter(stores, this, GlobalData.getInstance.userEmail!!)

            // recycleview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }
}