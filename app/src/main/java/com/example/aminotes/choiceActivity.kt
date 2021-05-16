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

        var str = subjectNameIntent
        if (str != null) {
            str = str.replace("\\s".toRegex(), "")
        }

        notes.setOnClickListener {
            var intent = Intent(this,PdfActivity::class.java)
            intent.putExtra(SUBJECTNAME,str)
            intent.putExtra(YEAR,yearIntent)
            intent.putExtra(NOTESORPAPER,"notes")
            startActivity(intent)
        }
        QuestionPapers.setOnClickListener {
            var intent = Intent(this,PdfActivity::class.java)
            intent.putExtra(SUBJECTNAME,str)
            intent.putExtra(YEAR,yearIntent)
            intent.putExtra(NOTESORPAPER,"papers")
            startActivity(intent)
        }
    }
}