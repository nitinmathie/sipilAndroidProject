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
import com.example.hopelastrestart1.adapter.CellClickListener3
import com.example.hopelastrestart1.adapter.UserRVAdapter
import com.example.hopelastrestart1.api.ApiService
import com.example.hopelastrestart1.base.ViewModelFactory
import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.model.GetUsers
import com.example.hopelastrestart1.ui.home.UpdateUserRoleActivity
import com.example.hopelastrestart1.ui.home.project.AddUserActivity
import com.example.hopelastrestart1.util.*
import com.example.hopelastrestart1.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class UsersFragment : Fragment(), KodeinAware, CellClickListener3 {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var recycleview: RecyclerView
    private lateinit var viewModel: UserViewModel
    private lateinit var orgName: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_users, container, false)
        recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // ViewModelProviders().get(ProjectViewModel)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RetrofitBuilder.apiClient().create(ApiService::class.java))
        ).get(UserViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        var mMediaUri = getActivity()?.intent?.data
        val username = requireActivity().intent.getStringExtra("username")
//        orgName = requireActivity().intent.getStringExtra("organization_name")


        //val username=intent.getStringExtra("username")

        /* val users by lazyDeferred {
             viewModel.users1(username, organization_name)
         }
         Coroutines.main {
             progress_bar.show()
             val orgs = users.await()
             orgs.observe(viewLifecycleOwner, Observer {
                 progress_bar.hide()
                 it.size.toString()
                 recycleview.adapter = UserRVAdapter(it,this , username)
                 // initRecyclerView(it.toOrganizationItem())
             })
         }*/
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_add_user)
        add.setOnClickListener {
            val intent = Intent(activity, AddUserActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }

    override fun onCellClickListener(user: UserAdded, username: String) {
        val intent = Intent(activity, UpdateUserRoleActivity::class.java)
        GlobalData.getInstance.user = user
        startActivity(intent)
    }

    private fun setUpObserver(getUsers: GetUsers) {
        viewModel.getUsers(getUsers).observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        resource.data?.let { resp -> retrieveList(resp.body()?.users!!) }
                    }
                    Status.ERROR -> {
                        // resource.data?.let { resp -> retrieveList(resp.body()?.users!!) }
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })


    }

    private fun setupViewModel() {

    }

    private fun retrieveList(users: List<UserAdded>) {
        if (users.size > 0) {
            // GlobalData.getInstance.projects = projects
            recycleview.adapter =
                UserRVAdapter(users, this, GlobalData.getInstance.userEmail!!)

            // recycleview.adapter = OrganizationAdapter(users, this, "her")
//  userList.addAll(users)
//adapter.notifyDataSetChanged()
        }
    }


    override fun onResume() {
        super.onResume()
        val getUsers = GetUsers(
            GlobalData.getInstance.userEmail!!,
            GlobalData.getInstance.token!!,
            GlobalData.getInstance.orgName.toString(),
        )
        setUpObserver(getUsers)
    }
}