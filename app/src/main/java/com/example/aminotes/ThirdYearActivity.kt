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

        list.add(subject("Working in Teams for Professional Excellence",R.color.blue1,"third"))
        list.add(subject("Discrete Mathematical Structure",R.color.blue2,"third"))
        list.add(subject("Analysis and Design of Algorithm",R.color.blue3,"third"))
        list.add(subject("Fundamentals of Mobile Computing",R.color.blue4,"third"))
        list.add(subject("In-House Practical Training",R.color.blue5,"third"))
        list.add(subject("Exploring the Networks",R.color.blue6,"third"))
        list.add(subject("Aptitude and Reasoning Ability",R.color.blue7,"third"))
        list.add(subject("The Joy of Computing using Python",R.color.blue8,"third"))
        list.add(subject("Advanced Java Programming",R.color.blue9,"third"))
        list.add(subject("Compiler Construction",R.color.blue10,"third"))
        list.add(subject("Computer Graphics",R.color.blue11,"third"))
        list.add(subject("Fundamentals of Machine Learning",R.color.blue12,"third"))
        list.add(subject("Programming & Employability Skills for Computer Engineering",R.color.blue13,"third"))
        list.add(subject("Software Engineering",R.color.blue14,"third"))
        list.add(subject("German (sem 5)",R.color.blue15,"third"))
        list.add(subject("German (sem 6)",R.color.blue16,"third"))
        list.add(subject("Spanish (sem 5)",R.color.blue17,"third"))
        list.add(subject("Spanish (sem 6)",R.color.blue18,"third"))
        list.add(subject("French (sem 5)",R.color.blue19,"third"))
        list.add(subject("French (sem 6)",R.color.blue20,"third"))
        list.add(subject("Japanese (sem 5)",R.color.blue21,"third"))
        list.add(subject("Japanese (sem 6)",R.color.blue22,"third"))
        list.add(subject("Chinese (sem 5)",R.color.blue23,"third"))
        list.add(subject("Chinese (sem 6)",R.color.blue24,"third"))
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