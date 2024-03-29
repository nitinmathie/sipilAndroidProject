import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.adapter.*
import com.example.hopelastrestart1.data.db.entities.Invoice
import com.example.hopelastrestart1.ui.home.project.tabs.StoreViewModel
import com.example.hopelastrestart1.ui.home.project.tabs.StoreViewModelFactory
import com.example.hopelastrestart1.util.Coroutines
import com.example.hopelastrestart1.util.hide
import com.example.hopelastrestart1.util.lazyDeferred
import com.example.hopelastrestart1.util.show
import kotlinx.android.synthetic.main.activity_organization.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

//Todo scrollable fragment displaying Material name, quantity available, Price per unit, Unit of measurement.

class AddedInventoryFragment: Fragment(), KodeinAware, CellClickListener_invoice {
    override val kodein by kodein()
    private val factory: StoreViewModelFactory by instance()
    private lateinit var viewModel: StoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_added_inventory, container, false)
        var recycleview = rootView.findViewById<RecyclerView>(R.id.recyclerview_added_inventory)
        // ViewModelProviders().get(ProjectViewModel)
        viewModel = ViewModelProviders.of(this, factory).get(StoreViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycleview.layoutManager = linearLayoutManager
        var mMediaUri = getActivity()?.intent?.data
        val username = requireActivity().intent.getStringExtra("username")
        val organization_name = requireActivity().intent.getStringExtra("organization_name")
        val project_name = requireActivity().intent.getStringExtra("project_name")
        val store_name = requireActivity().intent.getStringExtra("store_name")
        //val username=intent.getStringExtra("username")

        val invoices by lazyDeferred {
            viewModel.getAllAddedInvoices(username, organization_name, project_name, store_name)
        }
        Coroutines.main {
            progress_bar.show()
            val orgs = invoices.await()
            orgs.observe(viewLifecycleOwner, Observer {
                progress_bar.hide()
                it.size.toString()
                recycleview.adapter = InvoicesRVAdapter(it, this, username, organization_name, project_name, store_name)
            })
        }

        return rootView
    }


    override fun onCellClickListener(
        invoice: Invoice,
        username: String,
        organization_name: String,
        project_name: String,
        plan_name: String
    ) {
        TODO("Not yet implemented")
    }

}