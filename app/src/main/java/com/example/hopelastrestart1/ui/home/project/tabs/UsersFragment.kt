package com.example.hopelastrestart1.ui.home.project.tabs

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
import com.example.hopelastrestart1.adapter.CellClickListener3
import com.example.hopelastrestart1.adapter.UserRVAdapter
import com.example.hopelastrestart1.data.db.entities.UserAdded
import com.example.hopelastrestart1.ui.home.HomeActivity
import com.example.hopelastrestart1.ui.home.project.AddProjectActivity
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class UsersFragment  : Fragment(), KodeinAware, CellClickListener3 {
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_users, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // ViewModelProviders().get(ProjectViewModel)
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        var mMediaUri = getActivity()?.intent?.data
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        //val username=intent.getStringExtra("username")

        val users by lazyDeferred {
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
        }
        val add = rootView.findViewById<FloatingActionButton>(R.id.fab_add_user)
        add.setOnClickListener {
            val intent =Intent(activity, AddProjectActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("organization_name", organization_name)
            startActivity(intent)
        }
        return rootView
    }
    override fun onCellClickListener(user: UserAdded, username: String) {
        val intent =  Intent(activity, HomeActivity::class.java)
        intent.putExtra("user_name", user.username)
        intent.putExtra("user_id", user.user_id)
        val username=intent.getStringExtra("username")
        intent.putExtra("username", username)
        startActivity(intent)
    }
}