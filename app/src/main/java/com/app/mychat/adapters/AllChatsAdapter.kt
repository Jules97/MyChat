package com.app.mychat.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mychat.R
import com.app.mychat.activities.SingleChatActivity
import com.app.mychat.data.entitites.Users
import kotlinx.android.synthetic.main.chat_layout_item.view.*

class AllChatsAdapter(private val context: Context) :
    RecyclerView.Adapter<AllChatsAdapter.MyViewHolder>() {

    private var usersList = emptyList<Users>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_layout_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = usersList[position]

        holder.itemView.username.text = currentItem.name
        holder.itemView.time.text = currentItem.time
        holder.itemView.messageTv.text = currentItem.message

        val name: String = holder.itemView.username.text.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleChatActivity::class.java)
            intent.putExtra("name", name)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //for android versions less than 6
            context.startActivity(intent)
        }

    }

    fun setData(users: List<Users>) {
        this.usersList = users
        notifyDataSetChanged()
    }


}



