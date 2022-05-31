package com.unknownadii.injecteat.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.unknownadii.injecteat.CategoryAndDoctersActivity
import com.unknownadii.injecteat.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llSeeMoreCategory = view.findViewById<LinearLayout>(R.id.llSeeMoreCategory)
        val tvSeeMoreCategory = view.findViewById<TextView>(R.id.tvSeeMoreCategory)
        val ivSeeMoreCategory = view.findViewById<ImageView>(R.id.ivSeeMoreCategory)
        val llSeeMoreDocters = view.findViewById<LinearLayout>(R.id.llSeeMoreDocters)
        val tvSeeMoreDocters = view.findViewById<TextView>(R.id.tvSeeMoreDocters)
        val ivSeeMoreDocters = view.findViewById<ImageView>(R.id.ivSeeMoreDocters)

        llSeeMoreCategory.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }
        tvSeeMoreCategory.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }
        ivSeeMoreCategory.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }

        llSeeMoreDocters.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }
        tvSeeMoreDocters.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }
        ivSeeMoreDocters.setOnClickListener {
            startActivity(Intent(requireActivity(), CategoryAndDoctersActivity::class.java))
        }
    }

}