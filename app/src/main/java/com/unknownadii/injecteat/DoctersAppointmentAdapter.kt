package com.unknownadii.injecteat


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class DoctersAppointmentAdapter(
    private val mList: List<DataDocterAppointment>
) :
    RecyclerView.Adapter<DoctersAppointmentAdapter.ViewHolder>() {

    lateinit var context: Context

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_docters_appointment, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        if (position % 2 == 0) {
            holder.llColor.background = ContextCompat.getDrawable(context, R.color.light_purple)
        } else {
            holder.llColor.background = ContextCompat.getDrawable(context, R.color.lightest_purple)

        }
        // sets the image to the imageview from our itemHolder class
        holder.imagePerson.setImageResource(ItemsViewModel.imagePerson)
        holder.imageArrow.setImageResource(ItemsViewModel.imageDelete)
        holder.imageArrow.setOnClickListener {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
        }

        // sets the text to the textview from our itemHolder class
        holder.nameDocter.text = ItemsViewModel.docterName
        holder.categoryDocter.text = ItemsViewModel.category
        holder.categoryTypeDocter.text = ItemsViewModel.categoryType

        holder.appointmentDate.text = ItemsViewModel.date
        holder.appointmentDateType.text = ItemsViewModel.dateReturn

        holder.appointmentTime.text = ItemsViewModel.time
        holder.appointmentTimeType.text = ItemsViewModel.timeReturn

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val llColor = itemView.findViewById<LinearLayout>(R.id.llDoctersAppointment)
        val imagePerson = itemView.findViewById<ImageView>(R.id.iv_docterAppointmentProfile)
        val nameDocter = itemView.findViewById<TextView>(R.id.tv_docterAppointmentName)
        val categoryDocter = itemView.findViewById<TextView>(R.id.tv_docterAppointmentCategory)
        val categoryTypeDocter =
            itemView.findViewById<TextView>(R.id.tv_docterAppointmentCategoryType)
        val appointmentDate = itemView.findViewById<TextView>(R.id.tv_docterAppointmentDate)
        val appointmentDateType =
            itemView.findViewById<TextView>(R.id.tv_docterAppointmentReturnDate)
        val appointmentTime = itemView.findViewById<TextView>(R.id.tv_docterAppointmentTime)
        val appointmentTimeType =
            itemView.findViewById<TextView>(R.id.tv_docterAppointmentReturnTime)
        val imageArrow = itemView.findViewById<ImageView>(R.id.deleteDocterAppointment)
    }

}
