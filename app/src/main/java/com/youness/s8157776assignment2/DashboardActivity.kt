package com.youness.s8157776assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.youness.s8157776assignment2.viewmodel.DashboardViewModel
import androidx.lifecycle.ViewModelProvider
import com.youness.s8157776assignment2.adapter.EntityAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import com.youness.s8157776assignment2.model.Entity
import android.widget.Button
import android.widget.ProgressBar
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val keypass = intent.getStringExtra("keypass")
        if (keypass == null) {
            Toast.makeText(this, "Missing keypass", Toast.LENGTH_SHORT).show()
            return
        }

        adapter = EntityAdapter(emptyList()) { entity -> openDetails(entity) }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        viewModel.loadDashboard(keypass)

        viewModel.dashboardData.observe(this) { response ->
            progressBar.visibility = View.GONE
            adapter.updateData(response.entities)
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            logout()
        }


    }
    private fun openDetails(entity: Entity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("destination", entity.destination)
        intent.putExtra("country", entity.country)
        intent.putExtra("bestSeason", entity.bestSeason)
        intent.putExtra("popularAttraction", entity.popularAttraction)
        intent.putExtra("description", entity.description)
        startActivity(intent)
    }

    private fun logout() {
        val prefs = getSharedPreferences("user", MODE_PRIVATE)
        prefs.edit().clear().apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }


}