package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPdfDownloadBinding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel

class PdfDownloadFragment : Fragment(R.layout.fragment_pdf_download) {

    private lateinit var binding : FragmentPdfDownloadBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPdfDownloadBinding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(11)
        binding.btPdfDownload.setOnClickListener { 
            viewModel.getPdfUri { isSuccessful, uri ->
                if (isSuccessful){
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(uri)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}