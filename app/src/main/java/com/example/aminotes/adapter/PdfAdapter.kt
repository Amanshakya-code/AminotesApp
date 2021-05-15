package com.example.aminotes.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.R
import com.example.aminotes.ViewPdfFile
import com.example.aminotes.model.fileInfoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import kotlinx.android.synthetic.main.single_pdf_item.view.*
import kotlinx.android.synthetic.main.single_subject_item.view.*

class PdfAdapter(options: FirebaseRecyclerOptions<fileInfoModel>) : FirebaseRecyclerAdapter<fileInfoModel, PdfAdapter.pdfViewHolder>(
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
           pdfImage.setOnClickListener {
               var intent = Intent(pdfImage.context,ViewPdfFile::class.java)
               intent.putExtra("filename",model.name)
               intent.putExtra("fileurl",model.url)
               intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
               pdfImage.context.startActivity(intent)
           }
       }
    }

}