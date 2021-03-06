package com.example.aminotes

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(hasInternetConnection()){
            connectionStatus.visibility = View.GONE
            networkText.visibility = View.GONE
            texttitle.visibility = View.VISIBLE
            firstYear.visibility = View.VISIBLE
            secondYear.visibility = View.VISIBLE
            thirdYear.visibility = View.VISIBLE
            fourthYear.visibility = View.VISIBLE
            refreshbtn.visibility = View.GONE
        }
        else
        {
            connectionStatus.visibility = View.VISIBLE
            networkText.visibility = View.VISIBLE
            refreshbtn.visibility = View.VISIBLE
            texttitle.visibility = View.GONE
            firstYear.visibility = View.GONE
            secondYear.visibility = View.GONE
            thirdYear.visibility = View.GONE
            fourthYear.visibility = View.GONE
            Toast.makeText(this,"Check Your Connection..",Toast.LENGTH_LONG).show()
        }
        refreshbtn.setOnClickListener {
            if(hasInternetConnection()){
                connectionStatus.visibility = View.GONE
                networkText.visibility = View.GONE
                texttitle.visibility = View.VISIBLE
                firstYear.visibility = View.VISIBLE
                secondYear.visibility = View.VISIBLE
                thirdYear.visibility = View.VISIBLE
                fourthYear.visibility = View.VISIBLE
                refreshbtn.visibility = View.GONE
            }
            else
            {
                connectionStatus.visibility = View.VISIBLE
                networkText.visibility = View.VISIBLE
                refreshbtn.visibility = View.VISIBLE
                texttitle.visibility = View.GONE
                firstYear.visibility = View.GONE
                secondYear.visibility = View.GONE
                thirdYear.visibility = View.GONE
                fourthYear.visibility = View.GONE
                Toast.makeText(this,"Check Your Connection..",Toast.LENGTH_LONG).show()
            }
        }

        firstYear.setOnClickListener {
            startActivity(Intent(this,FirstYearActivity::class.java))
        }
        secondYear.setOnClickListener {
            startActivity(Intent(this,SecondYearActivity::class.java))
        }
        thirdYear.setOnClickListener {
            startActivity(Intent(this,ThirdYearActivity::class.java))
        }
        fourthYear.setOnClickListener {
            Toast.makeText(this,"Data Not Found :(\nWe will update this in future",Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.about_us, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutUs -> {
                val inflater = LayoutInflater.from(this)
                val view = inflater.inflate(R.layout.alert_dialog,null)
                val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
                    .setView(view)
                    .setCancelable(true)
                    .setPositiveButton("Connect With Me"){dialogInterface,which->
                        val url = "https://www.linkedin.com/in/amankumar007/"
                        val uri = Uri.parse(url)
                        startActivity(Intent(Intent.ACTION_VIEW,uri))
                        Toast.makeText(this,"For Any App Related Query You Can Reached To Me!!",Toast.LENGTH_LONG).show()
                    }
                    .create()
                alertDialog.show()
                return true
            }
            R.id.UploadFile->{
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Help Us In Providing the Data")
                dialogBuilder.setMessage("It would be great help :)")
                    .setCancelable(true)
                    .setPositiveButton("Send Us",){dialogInterFace,which->
                        val url = "https://docs.google.com/forms/d/e/1FAIpQLSfTH-RAIVAFz_ilSWibY_hk3Ew5smWzKnOueZnochiWoylSBA/viewform?usp=sf_link"
                        val uri = Uri.parse(url)
                        startActivity(Intent(Intent.ACTION_VIEW,uri))
                    }.create()
                val alert = dialogBuilder.create()
                alert.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hasInternetConnection():Boolean {
        val connectivityManager = getApplication().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?:return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}