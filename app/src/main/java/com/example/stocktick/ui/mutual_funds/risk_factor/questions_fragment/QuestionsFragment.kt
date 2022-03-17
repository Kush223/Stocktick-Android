package com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentQuestionsBinding
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorViewModel
import kotlin.math.ceil


private const val TAG = "QuestionsFragment"

class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private val viewModel: RiskFactorViewModel by activityViewModels()


    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var questionsRecyclerView: RecyclerView
    private val answers: MutableMap<String, String> = mutableMapOf()
    private var totalPage = 0

    private var allQuestions = listOf<Question>()

    private lateinit var adapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionsBinding.bind(view)
        questionsRecyclerView = binding.questions
        questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.mQuestions.observe(requireActivity(), Observer {
            Log.d(TAG, "onViewCreated: observed :$it")
            allQuestions = it

            val totalQuestions = allQuestions.size

            totalPage = ceil((totalQuestions.toDouble()) / 5.0).toInt()
            adapter = QuestionsAdapter.newInstance(
                list = allQuestions.take(5),
                answers = answers,
                context = requireContext(),
                onBtnClick = { page ->
                    when {
                        page >= totalPage -> {
                            val navController = view?.findNavController()
                            navController?.navigate(R.id.to_result_fragment)
                        }
                        page == totalPage - 1 -> {
                            adapter.questions = allQuestions.subList(
                                5 * page, allQuestions.size
                            )
                            adapter.notifyDataSetChanged()
                            questionsRecyclerView.smoothScrollToPosition(0)
                        }
                        else -> {
                            adapter.questions = allQuestions.subList(5 * (page), 5 * (page + 1))
                            adapter.notifyDataSetChanged()
                            questionsRecyclerView.smoothScrollToPosition(0)
                        }
                    }
                },
                totalPage = totalPage

            )

            questionsRecyclerView.adapter = adapter
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}