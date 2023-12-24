package com.kaliostech.booksnap

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var addBook: Button
    private lateinit var image_Chooser: Button
    private lateinit var img_selected: Uri
    private lateinit var edt_book_Name: EditText
    private lateinit var edt_whatilearn: EditText
    private lateinit var rv_booklist : RecyclerView
    private lateinit var rv_item_book_Name :String
    private lateinit var rv_item_whatilearn :String
    private var data = mutableListOf<Book>()
    private val adapter = bookAdapter(data)

    @SuppressLint("ResourceType", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_booklist = findViewById(R.id.rv_booklist)
        val myfab:View = findViewById(R.id.fab)

        // this creates a vertical layout Manager
        rv_booklist.layoutManager = LinearLayoutManager(this)
        // Setting the Adapter with the recyclerview
        rv_booklist.adapter = adapter

        myfab.setOnClickListener {
            openDialog()
        }

    }
    // OnCreate Ends Here
    @SuppressLint("MissingInflatedId")
    fun openDialog (){
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.customdiloag, null)
        addBook =view.findViewById(R.id.addBook)
        edt_book_Name = view.findViewById(R.id.et_bookName)
        edt_whatilearn = view.findViewById(R.id.whatilearn)
        image_Chooser = view.findViewById(R.id.img_chooser)
        builder.setView(view)
        val customDialog = builder.create()
        customDialog.show()
        // Img_chooser Called Here
        image_Chooser.setOnClickListener {
            openGallery()
        }
        addBook.setOnClickListener {
            rv_item_book_Name=edt_book_Name.text.toString()
            rv_item_whatilearn=edt_whatilearn.text.toString()
            data.add(Book(img_selected,rv_item_book_Name,rv_item_whatilearn))
            adapter.notifyDataSetChanged()

            customDialog.dismiss()
        }
    }

    // function for opening Dialog

    val getAction = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it!=null) {
            img_selected =it
        }
    }
    // Funtion for geting images from gallery
    private fun openGallery() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getAction.launch("image/*")
    }
}



