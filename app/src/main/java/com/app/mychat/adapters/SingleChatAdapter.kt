package com.app.mychat.adapters

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mychat.R
import com.app.mychat.data.entitites.Messages
import kotlinx.android.synthetic.main.message_layout.view.*
import java.util.concurrent.ThreadLocalRandom

class SingleChatAdapter(private val context: Context) : RecyclerView.Adapter<SingleChatAdapter.MyViewHolder>() {

    private var messageList = emptyList<Messages>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = messageList[position]

        val random: Double = ThreadLocalRandom.current().nextDouble(0.0, 0.5)

        holder.itemView.sent.text = currentItem.message
        holder.itemView.sTime.text = currentItem.time

        Handler().postDelayed({
            holder.itemView.echo.text = currentItem.message
            holder.itemView.eTime1.text = currentItem.time

            holder.itemView.echo2.text = currentItem.message
            holder.itemView.eTime2.text = currentItem.time
        }, random.toLong())

    }

    fun setData(messages: List<Messages>) {
        this.messageList = messages
        notifyDataSetChanged()
    }
}



