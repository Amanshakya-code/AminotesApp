package com.example.aminotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aminotes.adapter.PdfAdapter
import com.example.aminotes.model.fileInfoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.activity_pdf.*
import java.time.Year


const val SUBJECTNAME = "subjectname"
const val YEAR = "year"
const val NOTESORPAPER ="notesorpaper"
class PdfActivity : AppCompatActivity() {
    var year:String = ""
    var subjectName = ""
    var notesorpaper = ""
    private lateinit var adapter:PdfAdapter
    private val db: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        subjectName = intent.getStringExtra(SUBJECTNAME).toString()
        year = intent.getStringExtra(YEAR).toString()
        notesorpaper = intent.getStringExtra(NOTESORPAPER).toString()
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setTitle(notesorpaper)
        }
        questionPaperRecyclerView.layoutManager = LinearLayoutManager(this)

        val baseQuery: Query =
            db.reference.child("$year/$subjectName/$notesorpaper").child("pdf")
        val options = FirebaseRecyclerOptions.Builder<fileInfoModel>()
            .setQuery(baseQuery, fileInfoModel::class.java)
            .build()
        Log.i("datare","$options")
        adapter = PdfAdapter(options)
        questionPaperRecyclerView.adapter = adapter

    }
    fun OpenNewTask(view: View){
        var intent = Intent(this,UploadFile::class.java)
        intent.putExtra(SUBJECTNAME,subjectName)
        intent.putExtra(YEAR,year)
        intent.putExtra(NOTESORPAPER,notesorpaper)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }
}