package com.example.aminotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_choice.*
import java.time.Year

class choiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        var subjectNameIntent = intent.getStringExtra(SUBJECTNAME)
        var yearIntent = intent.getStringExtra(YEAR)
        subjectName.text = subjectNameIntent
        val actionbar = supportActionBar

        var subjectnameCompress = subjectNameIntent?.let { compress(it) }
        /*if (str != null) {
            str = str.replace("\\s".toRegex(), "")
        }*/

        notes.setOnClickListener {
            var intent = Intent(this,PdfActivity::class.java)
            intent.putExtra(SUBJECTNAME,subjectnameCompress)
            intent.putExtra(YEAR,yearIntent)
            intent.putExtra(NOTESORPAPER,"Notes")
            startActivity(intent)
        }
        QuestionPapers.setOnClickListener {
            var intent = Intent(this,PdfActivity::class.java)
            intent.putExtra(SUBJECTNAME,subjectnameCompress)
            intent.putExtra(YEAR,yearIntent)
            intent.putExtra(NOTESORPAPER,"Question Paper")
            startActivity(intent)
        }
    }
    private fun compress(sample:String):String{
        var compressed = ""
        if(sample!=null){
            compressed = sample.replace("\\s".toRegex(), "")
        }
        return compressed
    }
}