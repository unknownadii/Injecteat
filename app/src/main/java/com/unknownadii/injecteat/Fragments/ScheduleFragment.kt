package com.unknownadii.injecteat.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.quotes.spot.quotmotiv.DataBase.AppointmentData
import com.quotes.spot.quotmotiv.DataBase.DoctersDatabase
import com.unknownadii.injecteat.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private lateinit var database: DoctersDatabase
    private lateinit var list: List<AppointmentData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Room.databaseBuilder(
            requireActivity(),
            DoctersDatabase::class.java, "AppointmentDocters"
        ).build()

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.rvAppointment)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(requireContext())



        // ArrayList of class ItemsViewModel
        val data = ArrayList<DataDocterAppointment>()

        // This loop will create 20 Views containing
        // the image with the count of view
        /*
        database.doctersDAO().getAllAppointmentDocters().observe(requireActivity(),
            {

                for (i in 1..10) {
                    data.add(
                        DataDocterAppointment(
                            R.drawable.icon_docter, it.get(0).docterNameAppointment,
                            "Category : ", it.get(0).docterCategoryAppointment, "Date : ",
                            it.get(0).docterDateAppointment,
                            "Time : ", it.get(0).docterTimeAppointment, R.drawable.icon_delete
                        )
                    )
                }
            })


         */


            data.add(
                DataDocterAppointment(
                    R.drawable.icon_docter,"Dr.Aditya Raj Singh",
                    "Category : ", "Cardiologist", "Date : ",
                    "17/6/22",
                    "Time : ", "8:00 pm", R.drawable.icon_delete
                )
            )
        data.add(
            DataDocterAppointment(
                R.drawable.icon_docter,"Dr.Raj Singh",
                "Category : ", "ENT specialist", "Date : ",
                "19/6/22",
                "Time : ", "6:00 pm", R.drawable.icon_delete
            )
        )
        data.add(
            DataDocterAppointment(
                R.drawable.icon_docter,"Dr. Singh",
                "Category : ", "Paediatrician", "Date : ",
                "17/6/22",
                "Time : ", "8:00 pm", R.drawable.icon_delete
            )
        )
        data.add(
            DataDocterAppointment(
                R.drawable.icon_docter,"Dr. Rahul",
                "Category : ", "Radiologist", "Date : ",
                "15/6/22",
                "Time : ", "8:00 pm", R.drawable.icon_delete
            )
        )
        data.add(
            DataDocterAppointment(
                R.drawable.icon_docter,"Dr. Krishna",
                "Category : ", "Neurologist", "Date : ",
                "17/6/22",
                "Time : ", "4:00 pm", R.drawable.icon_delete
            )
        )


        val adapter = DoctersAppointmentAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}