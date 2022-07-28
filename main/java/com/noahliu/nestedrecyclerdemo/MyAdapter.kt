package com.example.f

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool


class MyAdapter internal constructor(
    myData: List<com.example.f.MyData>,
    onItemClick: OnItemClick
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(), NestedAdapter.OnChildClick {
    private val viewPool = RecycledViewPool()
    private val myData: List<com.example.f.MyData>
    private val onItemClick: OnItemClick

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val recyclerView: RecyclerView

        init {
            tvTitle = itemView.findViewById(R.id.textView_Title)
            recyclerView = itemView.findViewById(R.id.recyclerview_Nested)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.setText(myData[position].title)
        holder.recyclerView.adapter = NestedAdapter(
            myData[position].nesData, position, this
        )
        holder.recyclerView.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return myData.size
    }


    override fun onChildClick(data: com.example.f.MyData.NestedData?, parentPosition: Int) {
        onItemClick.onItemClick(data, myData[parentPosition])
    }

    internal interface OnItemClick {
        fun onItemClick(
            data: com.example.f.MyData.NestedData?,
            myData: com.example.f.MyData?
        )
    }

    init {
        this.myData = myData
        this.onItemClick = onItemClick
    }
}
