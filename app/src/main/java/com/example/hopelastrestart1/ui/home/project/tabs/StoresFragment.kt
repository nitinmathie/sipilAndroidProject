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
import com.example.hopelastrestart1.adapter.CellClickListener2
import com.example.hopelastrestart1.adapter.StoreRVAdapter
import com.example.hopelastrestart1.data.db.entities.Store
import com.example.hopelastrestart1.ui.home.HomeActivity
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class StoresFragment : Fragment(), KodeinAware, CellClickListener2 {
    override val kodein by kodein()
    private val factory: StoreViewModelFactory by instance()
    private lateinit var viewModel: StoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_stores, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        // ViewModelProviders().get(ProjectViewModel)
        viewModel = ViewModelProviders.of(this, factory).get(StoreViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val stores by lazyDeferred {
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
        }
        return rootView
    }
    override fun onCellClickListener(store: Store, username: String) {
        val intent =  Intent(activity, HomeActivity::class.java)
        intent.putExtra("store_name", store.store_name)
        intent.putExtra("store_id", store.store_id)
        val username=intent.getStringExtra("username")
        intent.putExtra("username", username)
        startActivity(intent)
    }
}