package com.example.aminotes

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_pdf_file.*
import java.net.URLEncoder

class ViewPdfFile : AppCompatActivity() {
    var mydownloadId :Long = 0
    var filename = ""
    var fileurl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf_file)
        webView.settings.javaScriptEnabled = true
        filename = intent.getStringExtra("filename").toString()
        fileurl = intent.getStringExtra("fileurl").toString()
        Log.i("urlmodel","${fileurl}")
         var progressDialog = ProgressDialog(this)
        progressDialog.setTitle(filename)
        progressDialog.setMessage("Opening.....!!")
        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webviewPb.visibility = View.GONE
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog.dismiss()
            }
        }
        var encodeurl = "";
        try {
            encodeurl = URLEncoder.encode(fileurl, "UTF-8")
        }
        catch (e: Exception){

        }
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + encodeurl)
        Log.i("urlmodel","${encodeurl}")


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.download, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.download -> {
                var request = DownloadManager.Request(
                    Uri.parse(fileurl)
                )
                    .setTitle(filename)
                    .setDescription(filename + "Downloading..")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)

                var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                mydownloadId = dm.enqueue(request)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}