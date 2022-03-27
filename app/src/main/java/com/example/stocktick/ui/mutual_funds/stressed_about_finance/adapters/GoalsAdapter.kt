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


val priorities = listOf(
    "High",
    "Medium",
    "Low"
)

private const val TAG = "GoalsAdapter"
class GoalsAdapter
    constructor(
        private val goals: MutableList<Goal>,
        private val onRemoveClicked: (position: Int)->Unit,
        private val context: Context
    )
    : RecyclerView.Adapter<GoalsAdapter.ViewHolder>() {

    companion object {
        fun getNewInstance(
            goals: MutableList<Goal>,
            onRemoveClicked: (position: Int) -> Unit,
            context: Context
        ) : GoalsAdapter {
            return GoalsAdapter(
                goals,
               onRemoveClicked,
                context
            )
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btRemove: ImageButton = itemView.findViewById(R.id.btRemove)
        val tvGoal: TextView = itemView.findViewById(R.id.tvGoal)
        val spPriority: Spinner = itemView.findViewById(R.id.spPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goals_layout,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = holder.absoluteAdapterPosition
        val goal = goals[pos]
        holder.tvGoal.text = goal.text
        holder.btRemove.setOnClickListener{
            Log.d(TAG, "onBindViewHolder: Click position :$pos and layout position :${holder.layoutPosition}")
            onRemoveClicked(
                holder.layoutPosition
            )
        }
        val adapter = ArrayAdapter(context, R.layout.gender_spinner_dropdown, priorities)
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        holder.spPriority.adapter = adapter
        holder.spPriority.setSelection(goal.priority)

        holder.spPriority.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                Log.d(TAG, "onItemSelected: click position :$pos and layout pos :${holder.layoutPosition}")
                try {
                    goals[holder.layoutPosition].priority = i
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "onItemSelected: ${e.localizedMessage}", )
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

    }

    override fun getItemCount(): Int {
        return goals.size
    }
}