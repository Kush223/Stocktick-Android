package com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import soup.neumorphism.NeumorphCardView


private const val TAG = "OptionsAdapterT"
class OptionsAdapter
constructor(
    private val options: List<String>,
    val onClick: (no: Int) -> Unit,
    var optionChosen : Int
)
    : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val option: TextView = itemView.findViewById(R.id.option)
        val optionCardView : NeumorphCardView = itemView.findViewById(R.id.optionCardView)
    }

    companion object {
        fun newInstance(
            options: List<String>,
            onClick: (no: Int) -> Unit,
            optionChosen: Int
        )= OptionsAdapter(
            options,
            onClick,
            optionChosen
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.option_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = options[position]
        Log.d(TAG, "onBindViewHolder: position: $position and chosen option :$optionChosen")
        holder.option.text = "${('a'.code.toByte().toInt()+position).toChar()}. $option"
        if (optionChosen == holder.layoutPosition) {
            holder.option.setTextColor(Color.parseColor("#FFC400"))
        }
        else holder.option.setTextColor(Color.WHITE)
        holder.optionCardView.setOnClickListener{
            Log.d(TAG, "onBindViewHolder: Clicked :$position")
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

}