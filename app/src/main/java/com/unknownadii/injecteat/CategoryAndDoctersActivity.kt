package com.unknownadii.injecteat

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAndDoctersActivity : AppCompatActivity(), DoctersAdapter.moveToDocterProfile {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar;
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_and_docters)

         toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarCategoryAndDoctersActivity)
        //for showing toolbar
        setSupportActionBar(toolbar)
        // for getting back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rvDocters)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<DataDocter>()

        // This loop will create 20 Views containing
        // the image with the count of view
        data.add(
            DataDocter(
                R.drawable.icon_docter, "Dr. Aditya Raj Singh",
                "Category: ",
                "Cardiologist",
                R.drawable.icon_right_arrow
            )
        )
        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Raj ",
                "Category: ",
                "ENT specialist",
                R.drawable.icon_right_arrow
            )
        )
        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Singh ",
                "Category: ",
                "Paediatrician",
                R.drawable.icon_right_arrow
            )
        )
        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Anurag ",
                "Category: ",
                "Psychiatrists",
                R.drawable.icon_right_arrow
            )
        )
        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Rahul ",
                "Category: ",
                "Radiologist",
                R.drawable.icon_right_arrow
            )
        )

        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Mohit ",
                "Category: ",
                "Neurologist",
                R.drawable.icon_right_arrow
            )
        )
        data.add(
            DataDocter(
                R.drawable.icon_docter,
                "Dr. Neeraj ",
                "Category: ",
                "Cardiothoracic surgeon",
                R.drawable.icon_right_arrow
            )
        )
        // This will pass the ArrayList to our Adapter
        val adapter = DoctersAdapter(data,this@CategoryAndDoctersActivity)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    fun doSomething(view: View) {
        showDialog()
        when(view.id) {
            R.id.categoryCardiologist ->{changeTitle("Cardiologist")}
            R.id.categoryOrthopaedic ->{changeTitle("Orthopaedic")}
            R.id.categoryPaediatician ->{changeTitle("Paediatrician")}
            R.id.categoryENTSpecialist ->{changeTitle("ENT Specialist")}
            R.id.categoryPsychiatrists ->{changeTitle("Psychiatrists")}
            R.id.categoryEndocrinologist ->{changeTitle("Endocrinologist")}
        }
        hideDialog()
    }

    private fun changeTitle(titleName : String) {
        toolbar.title = titleName
        val svCategory = findViewById<ScrollView>(R.id.svCategory)
        val llDocters = findViewById<LinearLayout>(R.id.llDocters)
        svCategory.visibility = View.GONE
        llDocters.visibility = View.VISIBLE
    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun hideDialog() {
        dialog.dismiss()
    }

    override fun redirect() {
        startActivity(Intent(this,DoctersProfileActivity::class.java))
    }
}