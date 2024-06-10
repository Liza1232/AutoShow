package com.example.autoshow

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.autoshow.R

class CarDetailActivity : AppCompatActivity() {

    private lateinit var carImageView: ImageView
    private lateinit var carBrandTextView: TextView
    private lateinit var carModelTextView: TextView
    private lateinit var carYearTextView: TextView
    private lateinit var carTypeTextView: TextView
    private lateinit var carMileageTextView: TextView
    private lateinit var carPriceTextView: TextView
    private lateinit var carDescriptionTextView: TextView
    private lateinit var saveCarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        carImageView = findViewById(R.id.carImageView)
        carBrandTextView = findViewById(R.id.carBrandTextView)
        carModelTextView = findViewById(R.id.carModelTextView)
        carYearTextView = findViewById(R.id.carYearTextView)
        carTypeTextView = findViewById(R.id.carTypeTextView)
        carMileageTextView = findViewById(R.id.carMileageTextView)
        carPriceTextView = findViewById(R.id.carPriceTextView)
        carDescriptionTextView = findViewById(R.id.carDescriptionTextView)
        saveCarButton = findViewById(R.id.saveCarButton)

        val carStatus = intent.getStringExtra("carStatus") ?: ""
        val carType = intent.getStringExtra("carType") ?: ""
        val carCountry = intent.getStringExtra("carCountry") ?: ""
        val carBrand = intent.getStringExtra("carBrand") ?: ""
        val carModel = intent.getStringExtra("carModel") ?: ""
        val carYear = intent.getIntExtra("carYear", 0)
        val carMileage = intent.getStringExtra("carMileage") ?: ""
        val carPrice = intent.getStringExtra("carPrice") ?: ""
        val carDescription = intent.getStringExtra("carDescription") ?: ""
        val carImageUri = intent.getStringExtra("carImageUri") ?: ""
        val saveCarButton: Button = findViewById(R.id.saveCarButton)

        carBrandTextView.text = carBrand
        carModelTextView.text = carModel
        carYearTextView.text = "Year: $carYear"
        carTypeTextView.text = "Type: $carType"
        carMileageTextView.text = "Mileage: $carMileage"
        carPriceTextView.text = "Price: $carPrice"
        carDescriptionTextView.text = "Description: $carDescription"

        val imageResource = resources.getIdentifier(carImageUri, "drawable", packageName)
        Glide.with(this)
            .load(imageResource)
            .into(carImageView)

        saveCarButton.setOnClickListener {
            val savedCar = Car(carStatus, carType, carCountry, carBrand, carModel, carYear, carMileage, carPrice, carDescription, carImageUri)
            val resultIntent = Intent().apply {
                putExtra("savedCar", savedCar)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        val orderConsultationButton: Button = findViewById(R.id.orderConsultationButton)
        orderConsultationButton.setOnClickListener {
            val url = "https://auto.ria.com/uk/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}
