package com.youness.s8157776assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val destination = intent.getStringExtra("destination")
        val country = intent.getStringExtra("country")
        val bestSeason = intent.getStringExtra("bestSeason")
        val popularAttraction = intent.getStringExtra("popularAttraction")
        val description = intent.getStringExtra("description")

        val tvDestination = findViewById<TextView>(R.id.tvDestination)
        val tvCountry = findViewById<TextView>(R.id.tvCountry)
        val tvBestSeason = findViewById<TextView>(R.id.tvBestSeason)
        val tvAttraction = findViewById<TextView>(R.id.tvAttraction)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)

        tvDestination.text = destination
        tvCountry.text = country
        tvBestSeason.text = bestSeason
        tvAttraction.text = popularAttraction
        tvDescription.text = description
    }
}
