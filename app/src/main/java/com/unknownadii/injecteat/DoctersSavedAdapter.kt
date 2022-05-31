package com.unknownadii.injecteat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class DoctersSavedAdapter(private val mList: List<DataDocter>) :
    RecyclerView.Adapter<DoctersSavedAdapter.ViewHolder>() {

    private lateinit var context: Context

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_docters_saved, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imagePerson.setImageResource(ItemsViewModel.imagePerson)
        holder.imageArrow.setImageResource(ItemsViewModel.imageArrow)
        holder.imageArrow.setOnClickListener {
            startActivity(
                context,
                Intent(context, DoctersProfileActivity::class.java), null
            )
        }

        if (position % 2 == 0) {
            holder.llSaved.background = ContextCompat.getDrawable(context, R.color.light_purple)
        } else {
            holder.llSaved.background = ContextCompat.getDrawable(context, R.color.lightest_purple)

        }
        // sets the text to the textview from our itemHolder class
        holder.nameDocter.text = ItemsViewModel.docterName
        holder.categoryDocter.text = ItemsViewModel.category
        holder.categoryTypeDocter.text = ItemsViewModel.categoryType

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imagePerson = itemView.findViewById<ImageView>(R.id.iv_personSaved)
        val llSaved = itemView.findViewById<LinearLayout>(R.id.llSaved)
        val nameDocter = itemView.findViewById<TextView>(R.id.tv_docterNameSaved)
        val categoryDocter = itemView.findViewById<TextView>(R.id.tv_categoryDocterSved)
        val categoryTypeDocter = itemView.findViewById<TextView>(R.id.tv_categoryTypeDocterSaved)
        val imageArrow = itemView.findViewById<ImageView>(R.id.iv_arrowDocterSaved)
    }

}