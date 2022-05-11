package com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment

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
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models.AnswersDto
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

        val tracker = (activity as RiskFactorActivity).customTracker
        tracker.setPosition(0)
        tracker.visibility = View.VISIBLE

        questionsRecyclerView = binding.questions
        questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllQuestions()

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
                        page >= totalPage -> {   //This is submit button

                            if (
                                !validateResponse(
                                    start = (page - 1) * 5 + 1,
                                    end = totalQuestions
                                )
                            ) {
                                Toast.makeText(
                                    requireContext(),
                                    "Please answer all the questions",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@newInstance false
                            }
                            tracker.move()



                            val postUserResponse = AnswersDto(
                                option1 = answers["option1"] ?: "1",
                                option2 = answers["option2"] ?: "1",
                                option3 = answers["option3"] ?: "1",
                                option4 = answers["option4"] ?: "1",
                                option5 = answers["option5"] ?: "1",
                                option6 = answers["option6"] ?: "1",
                                option7 = answers["option7"] ?: "1",
                                option8 = answers["option8"] ?: "1",
                                option9 = answers["option9"] ?: "1",
                                option10 = answers["option10"] ?: "1",
                            )
                            viewModel.postUserResponse(
                                answersDto = postUserResponse
                            ) { isSuccessful ->
                                if (isSuccessful) {
                                    val navController = view?.findNavController()
                                    navController?.navigate(R.id.to_result_fragment)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Failed to submit response",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }
                        page == totalPage - 1 -> { //this is for the last page, logic here populates the adapter with the remaining no of questions which would be 5 or less

                            if (
                                !validateResponse(
                                    start = (page - 1) * 5 + 1,
                                    end = page * 5
                                )
                            ) {
                                Toast.makeText(
                                    requireContext(),
                                    "Please answer all the questions",
                                    Toast.LENGTH_SHORT
                                ).show()

                                return@newInstance false

                            }
                            tracker.move()


                            adapter.questions = allQuestions.subList(
                                5 * page, allQuestions.size
                            )
                            adapter.notifyDataSetChanged()
                            questionsRecyclerView.smoothScrollToPosition(0)
                        }
                        else -> {           //this is for usual page

                            if (
                                !validateResponse(
                                    start = (page - 1) * 5 + 1,
                                    end = page * 5
                                )
                            ) {
                                Toast.makeText(
                                    requireContext(),
                                    "Please answer all the questions",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@newInstance false
                            }
                            tracker.move()

                            adapter.questions = allQuestions.subList(5 * (page), 5 * (page + 1))
                            adapter.notifyDataSetChanged()
                            questionsRecyclerView.smoothScrollToPosition(0)
                        }
                    }
                    true
                },
                totalPage = totalPage

            )

            questionsRecyclerView.adapter = adapter
        })

    }

    private fun validateResponse(start: Int, end: Int): Boolean {
        for (i in start..end) {
            if (!answers.containsKey("option$i")) return false
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}