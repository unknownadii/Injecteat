package com.unknownadii.injecteat.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unknownadii.injecteat.DataDocter
import com.unknownadii.injecteat.DoctersAdapter
import com.unknownadii.injecteat.DoctersSavedAdapter
import com.unknownadii.injecteat.R

class SavedFragment : Fragment(R.layout.fragment_saved) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.rvSaved)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

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
        val adapter = DoctersSavedAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

}