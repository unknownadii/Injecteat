package com.unknownadii.injecteat


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.unknownadii.injecteat.Fragments.DocterAboutFragment
import com.unknownadii.injecteat.Fragments.DocterScheduleFragment
import com.unknownadii.injecteat.Fragments.ReviewsDocterFragment

class DoctersProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docters_profile)

        val cross = findViewById<ImageView>(R.id.crossDocterProfile)
        cross.setOnClickListener {
            onBackPressed()
            finish()
        }

        val docterAboutFragment = DocterAboutFragment()
        val docterScheduleFragment = DocterScheduleFragment()
        val docterReviewsFragment = ReviewsDocterFragment()
        setCurrentFragment(docterScheduleFragment)

        val tvScheduleDocterProfile = findViewById<TextView>(R.id.tvScheduleDocterProfile)
        val tvAboutDocterProfile = findViewById<TextView>(R.id.tvAboutDocterProfile)
        val tvReviewsDocterProfile = findViewById<TextView>(R.id.tvReviewsDocterProfile)

        val underLineSchedule = findViewById<View>(R.id.underlineDocterProfile)
        val underLineAbout = findViewById<View>(R.id.underlineAboutDocterProfile)
        val underLineReview = findViewById<View>(R.id.underlineReviewsDocterProfile)

        tvScheduleDocterProfile.setOnClickListener {
            setCurrentFragment(docterScheduleFragment)
            underLineSchedule.visibility = View.VISIBLE
            underLineAbout.visibility = View.GONE
            underLineReview.visibility = View.GONE
        }
        tvAboutDocterProfile.setOnClickListener {
            setCurrentFragment(docterAboutFragment)
            underLineSchedule.visibility = View.GONE
            underLineAbout.visibility = View.VISIBLE
            underLineReview.visibility = View.GONE
        }
        tvReviewsDocterProfile.setOnClickListener {
            setCurrentFragment(docterReviewsFragment)
            underLineSchedule.visibility = View.GONE
            underLineAbout.visibility = View.GONE
            underLineReview.visibility = View.VISIBLE
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.docterProfileFragment, fragment)
            commit()
        }
    }
}