package com.example.stocktick.ui.mutual_funds.risk_factor.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentResultBinding
import com.example.stocktick.ui.customviews.PerformanceMeter
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.File
import java.net.URL


class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private lateinit var meter : PerformanceMeter
    
    private val viewModel: RiskFactorViewModel by activityViewModels()

    private var file: File? = null





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        meter = binding.performanceMEter

        (activity as RiskFactorActivity).customTracker.visibility = View.GONE
        
        viewModel.getRangeResult{isSuccessful, getRangeResultDM ->  
            if (isSuccessful){
                val category = getRangeResultDM?.category ?: return@getRangeResult
                meter.swapAngle(category)
                binding.resultDesc.text = getRangeResultDM.description
                binding.score.text = "You have scored ${getRangeResultDM.score} out of 100"


            }
            else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btRetry.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_resultFragment_to_questionsFragment)
        }



        binding.btDownloadPdf.setOnClickListener{
            viewModel.getDownloadUrl{isSuccessful, url ->
                if (isSuccessful){
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                else Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()

            }

        }
        viewModel.getDownloadUrl{ success, url ->
            if (success) downloadInExtDir(URL(url))
        }

        binding.btShare.setOnClickListener{
            if (file == null) return@setOnClickListener
            val uri: Uri = FileProvider.getUriForFile(
                requireContext(),
                requireContext().packageName.toString() + ".provider",
                file!!
            )
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "application/pdf"
                putExtra(Intent.EXTRA_TEXT, "Risk Factor analysis result")
                putExtra(Intent.EXTRA_STREAM, uri)
            }
            startActivity(Intent.createChooser(intent,null))
        }
        

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun downloadInExtDir(url: URL){
        file = File.createTempFile("RiskFactor", ".pdf",requireActivity().getExternalFilesDir(null)) ?: return
        lifecycleScope.launch(Dispatchers.IO){
            BufferedInputStream(url.openStream()).use {
                val fos = file!!.outputStream()
                val dataBuffer = ByteArray(1024)
                var bytesRead = it.read(dataBuffer, 0,1024)
                while (bytesRead  != -1){
                    fos.write(dataBuffer, 0, bytesRead)
                    bytesRead = it.read(dataBuffer, 0 , 1024)
                }
                fos.close()

            }
        }

    }

}