package com.example.aminotes

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aminotes.model.fileInfoModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload_file.*

class UploadFile : AppCompatActivity() {
    lateinit var filePath: Uri
    private val storage by lazy {
        FirebaseStorage.getInstance()
    }
    private val db: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    private var year = ""
    private var subjectName = ""
    private var notesorpaper = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_file)
        year = intent.getStringExtra(YEAR).toString()
        subjectName = intent.getStringExtra(SUBJECTNAME).toString()
        notesorpaper = intent.getStringExtra(NOTESORPAPER).toString()
        pdfSelectedImage.visibility = View.INVISIBLE
        cancelButton.visibility = View.INVISIBLE

        cancelButton.setOnClickListener {
            pdfSelectedImage.visibility = View.INVISIBLE
            cancelButton.visibility = View.INVISIBLE
            browseButton.visibility = View.VISIBLE
            editText.text.clear()
        }
        browseButton.setOnClickListener {
            checkPermissionForFile()
        }
        uploadFileButton.setOnClickListener {
            uploadFile(filePath)
        }
    }

    private fun uploadFile(filePath: Uri) {
        var reference = storage.reference.child("uploads/$subjectName${System.currentTimeMillis()}.pdf")
        reference.putFile(filePath).addOnSuccessListener {
            reference.downloadUrl.addOnSuccessListener {
                val filemap = fileInfoModel(editText.text.toString(),it.toString())
                db.reference.child("$year/$subjectName/$notesorpaper").child("pdf/${System.currentTimeMillis()}").setValue(filemap).addOnSuccessListener {
                    Toast.makeText(this,"file Uploaded",Toast.LENGTH_SHORT).show()
                    pdfSelectedImage.visibility = View.INVISIBLE
                    cancelButton.visibility = View.INVISIBLE
                    browseButton.visibility = View.VISIBLE
                    editText.text.clear()
                }
            }
        }
    }

    private fun checkPermissionForFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionWrite = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(
                    permission,
                    1001
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(
                    permissionWrite,
                    1002
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                pickFromPhone()
            }
        }
    }

    private fun pickFromPhone() {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        startActivityForResult(
            intent, 1000
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000 && resultCode== RESULT_OK){
            if (data != null) {
                Log.i("occ","entry")
                filePath = data.data!!
                pdfSelectedImage.visibility = View.VISIBLE
                cancelButton.visibility = View.VISIBLE
                browseButton.visibility = View.INVISIBLE
            }
        }
    }
}