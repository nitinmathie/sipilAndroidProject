package com.example.hopelastrestart1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.data.db.entities.UserAdded
import kotlinx.android.synthetic.main.item_organization.view.createdby
import kotlinx.android.synthetic.main.item_organization.view.name

class UserRVAdapter(val users: List<UserAdded>,
                     private val cellClickListener: CellClickListener3,
                     val username:String):RecyclerView.Adapter<UserRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : UserRVAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
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
        val item = users[position]
        holder?.name.text = item.user_email.toString()
        holder?.id.text = item.user_role.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item, username)
        }
    }

}
interface  CellClickListener3 {
    fun onCellClickListener(user: UserAdded, username: String)
}
