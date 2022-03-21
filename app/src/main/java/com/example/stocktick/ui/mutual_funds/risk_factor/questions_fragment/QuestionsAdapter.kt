package com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment.QuestionsAdapter.*
import soup.neumorphism.NeumorphButton


private const val TAG = "QuestionsAdapter"
class QuestionsAdapter
constructor(
    var questions: List<Question>,
    private val answers: MutableMap<String, String>,
    private val context: Context,
    private val onBtnClick: (Int)->Boolean,
    private var page: Int=1,
    private val totalPage: Int

    ) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        fun newInstance(
            list: List<Question>,
            answers: MutableMap<String, String>,
            context: Context,
            onBtnClick: (Int) -> Boolean,
            totalPage: Int
        ) = QuestionsAdapter(
            list,
            answers,
            context,
            onBtnClick,
            totalPage = totalPage
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var question: TextView
        lateinit var options: RecyclerView
        lateinit var button: NeumorphButton

        init {
            try {

                 question = itemView.findViewById(R.id.question)
                 options = itemView.findViewById(R.id.options)
            }
            catch (e: Exception){
                Log.d(TAG, "Error question :${e.localizedMessage} ")
            }
            try {

                button = itemView.findViewById(R.id.btSubNext)
            }
            catch (e: Exception){
                Log.e(TAG, "Error view holder btn :${e.localizedMessage} ")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == questions.size) R.layout.neumorph_button else R.layout.question
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (viewType == R.layout.question)
            LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        else LayoutInflater.from(parent.context).inflate(R.layout.neumorph_button, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder: position :$position and size :${questions.size}")
        
        if (position == questions.size) {
            Log.d(TAG, "onBindViewHolder: button part")
            if (page == totalPage)
                holder.button.text = "Submit"
            else holder.button.text = "Next"
            holder.button.setOnClickListener(View.OnClickListener {
                val isSuccessful = onBtnClick(page)
                if (isSuccessful) page++
            })
        } else {
            Log.d(TAG, "onBindViewHolder: question part")
            val question = questions[position]
            val key = "option${question.questionNo}"
            var adapter : OptionsAdapter? = null
            adapter = OptionsAdapter.newInstance(
                options = question.options,
                onClick = {
                    //question numbering starts for 1 and not 0
                    answers[key] = (it+1).toString()
                    adapter?.optionChosen = it
                    adapter?.notifyDataSetChanged()
                    Log.d(TAG, "onBindViewHolder: $answers")
                },
                optionChosen = if (answers.containsKey(key) && answers[key]!=null) answers[key]!!.toInt() else -1
            )
            holder.question.text = "${position + 1 +(page-1)*5}. ${question.question}"
            holder.options.layoutManager = LinearLayoutManager(context)
            holder.options.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return questions.size+1
    }
}