package com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Goal
import java.lang.IndexOutOfBoundsException




private const val TAG = "GoalsAdapter"
class GoalsAdapter2
    constructor(
        private val goals: MutableList<String>,
        private val onRemoveClicked: (position: Int)->Unit,
        private val context: Context
    )
    : RecyclerView.Adapter<GoalsAdapter2.ViewHolder>() {

    companion object {
        fun getNewInstance(
            goals: MutableList<String>,
            onRemoveClicked: (position: Int) -> Unit,
            context: Context
        ) : GoalsAdapter2 {
            return GoalsAdapter2(
                goals,
               onRemoveClicked,
                context
            )
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btRemove: ImageButton = itemView.findViewById(R.id.btRemove)
        val tvGoal: TextView = itemView.findViewById(R.id.tvGoal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goals_layout2,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = holder.absoluteAdapterPosition
        val goal = goals[pos]
        holder.tvGoal.text = goal
        holder.btRemove.setOnClickListener{
            Log.d(TAG, "onBindViewHolder: Click position :$pos and layout position :${holder.layoutPosition}")
            onRemoveClicked(
                holder.layoutPosition
            )
        }

    }

    override fun getItemCount(): Int {
        return goals.size
    }
}