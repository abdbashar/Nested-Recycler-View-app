package com.example.f

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


internal class NestedAdapter(
    nestedData: List<MyData.NestedData>,
    parentPosition: Int,
    childClick: OnChildClick
) :
    RecyclerView.Adapter<NestedAdapter.NesHolder>() {
    private val nestedData: List<MyData.NestedData>
    private val childClick: OnChildClick
    private val parentPosition: Int

    inner class NesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.textView_nesTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesHolder {
        return NesHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nested, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NesHolder, position: Int) {
        val item: MyData.NestedData = nestedData[position]
        holder.tvTitle.setText(item.nesTitle)
        holder.itemView.setOnClickListener { v: View? ->
             childClick.onChildClick(item, parentPosition)
        }
    }

    override fun getItemCount(): Int {
        return nestedData.size
    }

    internal interface OnChildClick {
        fun onChildClick(data: MyData.NestedData?, parentPosition: Int)
    }

    init {
        this.nestedData = nestedData
        this.childClick = childClick
        this.parentPosition = parentPosition
    }
}
