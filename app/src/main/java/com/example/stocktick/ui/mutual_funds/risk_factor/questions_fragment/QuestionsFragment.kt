package com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentQuestionsBinding
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorViewModel
import kotlin.math.ceil


class QuestionsFragment : Fragment(R.layout.fragment_questions)
{

    private val viewModel: RiskFactorViewModel by activityViewModels()


    private lateinit var binding : FragmentQuestionsBinding
    private lateinit var questionsRecyclerView: RecyclerView
    private val answers : MutableMap<String, String> = mutableMapOf()
    private var fiveQuestions : List<Question> =  listOf()
    private var totalPage = 0

    private lateinit var adapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentQuestionsBinding.bind(view)
        questionsRecyclerView = binding.questions
        questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        fiveQuestions = viewModel.mQuestions.value?.take(5) ?: listOf()
        val totalQuestions = viewModel.mQuestions.value?.size ?: 0
        totalPage =  ceil((totalQuestions.toDouble()) / 5.0).toInt()
        adapter = QuestionsAdapter.newInstance(
            list = fiveQuestions,
            answers = answers,
            context = requireContext(),
            onBtnClick = { page->
                when {
                    page >= totalPage -> {
                        Toast.makeText(requireContext(), "Navigate now", Toast.LENGTH_SHORT).show()
                        view?.findNavController()?.navigate(R.id.to_result_fragment)
                    }
                    page==totalPage-1 -> {
                        val list = viewModel.mQuestions.value ?: listOf()
                        fiveQuestions = list.subList(
                            5*page, list.size-1
                        )
                        adapter.notifyDataSetChanged()
                        questionsRecyclerView.smoothScrollToPosition(0)
                    }
                    else -> {
                        fiveQuestions =
                            viewModel.mQuestions.value?.subList(5 * (page), 5 * (page + 1)) ?: listOf()
                        adapter.notifyDataSetChanged()
                        questionsRecyclerView.smoothScrollToPosition(0)
                    }
                }
            },
            totalPage = totalPage

        )

        questionsRecyclerView.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}