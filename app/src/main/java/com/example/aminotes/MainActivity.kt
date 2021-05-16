package com.example.aminotes

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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