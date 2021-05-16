package com.example.aminotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aminotes.adapter.SubjectAdpater
import com.example.aminotes.model.subject

class SecondYearActivity : AppCompatActivity() {
    lateinit var recylerView: RecyclerView
    var list = arrayListOf<subject>()
    var searchList = arrayListOf<subject>()
    lateinit var adapter: SubjectAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_year)

        list.add(subject("Applied Mathematics-3",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Applied Mathematics-4",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Basic Electronics Engineering",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Basic simulation lab ",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Data structure using c",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Digital Electronics and computer organization",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Object oriented programming using C++",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Discrete Mathmatical structure",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Java Programming",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Material science",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Operating System",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Self-reliance and socialization",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Theory of computation",R.drawable.ic_launcher_background,"second"))
        list.add(subject("German (sem 3)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("German (sem 4)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Spanish (sem 3)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Spanish (sem 4)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("French (sem 3)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("French (sem 4)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Japanese (sem 3)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Japanese (sem 4)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Chinese (sem 3)",R.drawable.ic_launcher_background,"second"))
        list.add(subject("Chinese (sem 4)",R.drawable.ic_launcher_background,"second"))
        recylerView = findViewById<RecyclerView>(R.id.secondYearRv)
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