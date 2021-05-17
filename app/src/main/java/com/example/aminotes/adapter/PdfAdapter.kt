package com.example.aminotes.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.R
import com.example.aminotes.ViewPdfFile
import com.example.aminotes.model.fileInfoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.paging.DatabasePagingOptions
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter
import com.firebase.ui.database.paging.LoadingState
import kotlinx.android.synthetic.main.single_pdf_item.view.*
import kotlinx.android.synthetic.main.single_subject_item.view.*

class PdfAdapter(options: DatabasePagingOptions<fileInfoModel>) : FirebaseRecyclerPagingAdapter<fileInfoModel, PdfAdapter.pdfViewHolder>(
    options
) {
    class pdfViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       //var subjectName = itemView.findViewById<TextView>(R.id.subjectName)
        /*fun bind(item:fileInfoModel) = with(itemView){
            pdfName.text = item.name
           Log.i("urlmodel","${item.url}")
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pdfViewHolder {
        return pdfViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_pdf_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: pdfViewHolder, position: Int, model: fileInfoModel) {
      //  holder.bind(model)
       holder.itemView.apply {
           pdfName.text = model.name
           pdfitem.setOnClickListener {
               var intent = Intent(pdfImage.context,ViewPdfFile::class.java)
               intent.putExtra("filename",model.name)
               intent.putExtra("fileurl",model.url)
               intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
               pdfImage.context.startActivity(intent)
           }
       }
    }

    override fun onLoadingStateChanged(state: LoadingState) {
        when(state)
        {
            LoadingState.LOADING_INITIAL -> {
                
            }

            LoadingState.LOADING_MORE -> {
            }

            LoadingState.LOADED -> {
            }

            LoadingState.ERROR -> {

            }

            LoadingState.FINISHED -> {
            }
        }
    }

}