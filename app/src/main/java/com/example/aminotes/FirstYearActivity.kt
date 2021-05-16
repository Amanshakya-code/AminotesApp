package com.example.aminotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.adapter.SubjectAdpater
import com.example.aminotes.model.subject

class FirstYearActivity : AppCompatActivity() {
    lateinit var recylerView:RecyclerView
    var list = arrayListOf<subject>()
    var searchList = arrayListOf<subject>()
    lateinit var adapter:SubjectAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_year)
        list.add(subject("Applied Mathematics-1",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Applied Mathematics-2",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Engineering Physics",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Engineering chemistry",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Engineering Mechanics",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Technical communication-1",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Technical communication-2",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Introduction to computers and programming in c",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Introduction to Environmental Studies",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Engineering Graphics ",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Basic electrical engineering",R.drawable.ic_launcher_background,"first"))
        list.add(subject("German (sem 1)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("German (sem 2)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Spanish (sem 1)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Spanish (sem 2)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("French (sem 1)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("French (sem 2)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Japanese (sem 1)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Japanese (sem 2)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Chinese (sem 1)",R.drawable.ic_launcher_background,"first"))
        list.add(subject("Chinese (sem 2)",R.drawable.ic_launcher_background,"first"))
        recylerView = findViewById<RecyclerView>(R.id.firstYearRv)
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