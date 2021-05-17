package com.example.aminotes

import android.Manifest
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_view_pdf_file.*
import java.net.URLEncoder

class ViewPdfFile : AppCompatActivity() {
    var mydownloadId :Long = 0
    var filename = ""
    var fileurl = ""
    private var encodeUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf_file)
        filename = intent.getStringExtra("filename").toString()
        fileurl = intent.getStringExtra("fileurl").toString()
        Log.i("urlmodel","${fileurl}")
         var progressDialog = ProgressDialog(this)
        progressDialog.setTitle(filename)
        progressDialog.setMessage("Opening.....!!")
        Toast.makeText(this,"Refresh Pdf if required...",Toast.LENGTH_SHORT).show()
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog.dismiss()
            }
        }
        try {
            encodeUrl = URLEncoder.encode(fileurl, "UTF-8")
        }
        catch (e: Exception){

        }
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + encodeUrl)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.download, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.download -> {
                checkPermissionForFile()
                return true
            }
            R.id.Refresh->{
                webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + encodeUrl)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun checkPermissionForFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permissionWrite = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(
                    permissionWrite,
                    1002
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                downLoadFile()
            }
        }
    }
    private fun downLoadFile(){
        var request = DownloadManager.Request(
            Uri.parse(fileurl)
        )
            .setTitle(filename)
            .setDescription(filename + "Downloading..")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename+".pdf")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)

        var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        mydownloadId = dm.enqueue(request)
    }

}