package com.example.aminotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aminotes.adapter.PdfAdapter
import com.example.aminotes.model.fileInfoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.paging.DatabasePagingOptions
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
    var str  = ""
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
        str = notesorpaper
        if (str != null) {
            str = str.replace("\\s".toRegex(), "")
        }
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setTitle(notesorpaper)
        }
        questionPaperRecyclerView.layoutManager = LinearLayoutManager(this)

        val config = PagedList.Config.Builder()
                .setPrefetchDistance(4) // number of pages you want initally
                .setPageSize(1)
                .setEnablePlaceholders(false)
                .build()
        val baseQuery =
            db.reference.child("$year/$subjectName/$str").child("pdf")
        val options = DatabasePagingOptions.Builder<fileInfoModel>()
                .setLifecycleOwner(this)
            .setQuery(baseQuery,config, fileInfoModel::class.java)
            .build()

        Log.i("datare","$options")
        adapter = PdfAdapter(options)
        questionPaperRecyclerView.adapter = adapter

    }
    /*fun OpenNewTask(view: View) {
            var intent = Intent(this, UploadFile::class.java)
            intent.putExtra(SUBJECTNAME, subjectName)
            intent.putExtra(YEAR, year)
            intent.putExtra(NOTESORPAPER, str)
            startActivity(intent)
        }*/

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }
}