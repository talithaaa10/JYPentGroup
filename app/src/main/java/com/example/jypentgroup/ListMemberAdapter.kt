package com.example.jypentgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMemberAdapter(private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, role, photo) = listMember[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvRole.text = role
    }

    override fun getItemCount(): Int = listMember.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_member_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_member_name)
        val tvRole: TextView = itemView.findViewById(R.id.tv_member_role)
    }
}