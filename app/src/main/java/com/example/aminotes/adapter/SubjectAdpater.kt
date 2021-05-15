package com.example.aminotes.adapter

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.PdfActivity
import com.example.aminotes.R
import com.example.aminotes.SUBJECTNAME
import com.example.aminotes.YEAR
import com.example.aminotes.model.subject
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.single_pdf_item.view.*
import kotlinx.android.synthetic.main.single_subject_item.view.*

class SubjectAdpater(private val list:List<subject>): RecyclerView.Adapter<SubjectAdpater.subjectViewHolder>() {

    lateinit var cardView: CardView
    class subjectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val subjectName = itemView.subjectName
        val subjectImage = itemView.subjectImage
        val cardView = itemView.cardview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subjectViewHolder {
        return subjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_subject_item,parent,false))
    }

    override fun onBindViewHolder(holder: subjectViewHolder, position: Int) {
        val currentSubject = list[position]
        holder.subjectImage.setImageResource(currentSubject.subjectImage)
        holder.subjectName.text = currentSubject.subjectName
        cardView = holder.cardView
        cardView.setOnClickListener {
            var intent = Intent(cardView.context,PdfActivity::class.java)
            var str = currentSubject.subjectName
            str = str.replace("\\s".toRegex(), "")
            intent.putExtra(SUBJECTNAME,str)
            intent.putExtra(YEAR,currentSubject.year)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            cardView.context.startActivity(intent)
        }
    }
    override fun getItemCount() = list.size
}