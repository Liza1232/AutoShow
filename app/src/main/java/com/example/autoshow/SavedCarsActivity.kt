package com.example.autoshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshow.R

class SavedCarsActivity : AppCompatActivity() {

    private lateinit var savedCarsRecyclerView: RecyclerView
    private lateinit var savedCarAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_cars)

        savedCarsRecyclerView = findViewById(R.id.savedCarsRecyclerView)
        savedCarsRecyclerView.layoutManager = LinearLayoutManager(this)

        val savedCars = intent.getParcelableArrayListExtra<Car>("savedCars") ?: arrayListOf()
        val savedCarsRecyclerView: RecyclerView = findViewById(R.id.savedCarsRecyclerView)
        savedCarAdapter = CarAdapter(this, savedCars) { car ->
            // Handle item click if needed
        }
        savedCarsRecyclerView.adapter = savedCarAdapter

        val orderConsultationButton: Button = findViewById(R.id.orderConsultationButton)
        orderConsultationButton.setOnClickListener {
            val url = "https://auto.ria.com/uk/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

}
