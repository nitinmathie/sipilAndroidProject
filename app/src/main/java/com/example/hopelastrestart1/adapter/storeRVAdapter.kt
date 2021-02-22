package com.example.hopelastrestart1.adapter

import com.example.hopelastrestart1.data.db.entities.Store
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import kotlinx.android.synthetic.main.item_organization.view.*

class StoreRVAdapter(val stores: List<Store>,
                     private val cellClickListener: CellClickListener2,
                     val username:String):RecyclerView.Adapter<StoreRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : StoreRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return stores.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id = itemView.name
        val name = itemView.createdby

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stores[position]
        holder?.name.text = item.store_name.toString()
        holder?.id.text = item.store_id.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username)
        }
    }

}
interface  CellClickListener2 {
    fun onCellClickListener(store: Store, username: String)
}

