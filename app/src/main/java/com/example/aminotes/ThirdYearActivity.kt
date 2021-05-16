package com.example.aminotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.adapter.SubjectAdpater
import com.example.aminotes.model.subject

class ThirdYearActivity : AppCompatActivity() {
    lateinit var recylerView: RecyclerView
    var list = arrayListOf<subject>()
    var searchList = arrayListOf<subject>()
    lateinit var adapter: SubjectAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_year)

        list.add(subject("Working in Teams for Professional Excellence",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Discrete Mathematical Structure",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Analysis and Design of Algorithm",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Fundamentals of Mobile Computing",R.drawable.ic_launcher_background,"third"))
        list.add(subject("In-House Practical Training",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Exploring the Networks",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Aptitude and Reasoning Ability",R.drawable.ic_launcher_background,"third"))
        list.add(subject("The Joy of Computing using Python",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Advanced Java Programming",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Compiler Construction",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Computer Graphics",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Fundamentals of Machine Learning",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Programming & Employability Skills for Computer Engineering",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Software Engineering",R.drawable.ic_launcher_background,"third"))
        list.add(subject("German (sem 3)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("German (sem 4)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Spanish (sem 3)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Spanish (sem 4)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("French (sem 3)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("French (sem 4)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Japanese (sem 3)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Japanese (sem 4)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Chinese (sem 3)",R.drawable.ic_launcher_background,"third"))
        list.add(subject("Chinese (sem 4)",R.drawable.ic_launcher_background,"third"))
        recylerView = findViewById<RecyclerView>(R.id.thirdYearRv)
        recylerView.layoutManager = LinearLayoutManager(this)
        adapter = SubjectAdpater(list)
        recylerView.adapter = adapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        var menuitem = menu?.findItem(R.id.search)
        var searchView = menuitem?.actionView as androidx.appcompat.widget.SearchView // returns the object of the searchView
        menuitem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                displaySearch()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                displaySearch()
                return true
            }

        })
        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!=null){
                    displaySearch(newText)
                }
                return false
            }
        })
        return true
    }

    private fun displaySearch(newText: String = "") {
        if(list.isNotEmpty()){
            searchList.clear()
            searchList.addAll(
                list.filter { subject ->
                    subject.subjectName.contains(newText,true)
                }
            )
            var adpter = SubjectAdpater(searchList)
            recylerView.adapter = adpter
        }
        adapter.notifyDataSetChanged()
    }
}