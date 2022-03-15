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
    private val questions: List<Question>,
    private val answers: MutableMap<String, String>,
    private val context: Context,
    private val onBtnClick: (Int)->Unit,
    private var page: Int=1,
    private val totalPage: Int

    ) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        fun newInstance(
            list: List<Question>,
            answers: MutableMap<String, String>,
            context: Context,
            onBtnClick: (Int) -> Unit,
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
                Log.d(TAG, "Error :${e.localizedMessage} ")
            }
            try {

                button = itemView.findViewById(R.id.btSubNext)
            }
            catch (e: Exception){
                Log.d(TAG, "Error :${e.localizedMessage} ")
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
        if (position == questions.size) {
            if (page == totalPage)
                holder.button.text = "Submit"
            else holder.button.text = "Next"
            holder.button.setOnClickListener(View.OnClickListener {
                onBtnClick(page)
                page++
            })
        } else {
            val question = questions[position]
            val adapter = OptionsAdapter.newInstance(
                options = question.options,
                onClick = {
                    //question numbering starts form 1 and not 0
                    val key = "option${question.questionNo}"
                    answers[key] = it.toString()
                    Toast.makeText(context, "OnBindVIewHOlder $answers", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onBindViewHolder: $answers")
                }
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