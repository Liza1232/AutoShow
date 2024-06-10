package com.example.autoshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshow.R

class MainActivity : AppCompatActivity() {

    private lateinit var carRecyclerView: RecyclerView
    private lateinit var carAdapter: CarAdapter
    private lateinit var statusSpinner: Spinner
    private lateinit var typeSpinner: Spinner
    private lateinit var countrySpinner: Spinner
    private lateinit var showSavedCarsButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    private val cars = listOf(
        Car("New", "Sedan", "Germany", "BMW", "3 Series", 2023, "10 km", "$50,000",
            "The BMW 3 Series is the epitome of luxury and performance in a sedan. This 2023 model comes with a sleek, aerodynamic design that turns heads on the road. Inside, you'll find a spacious and luxurious cabin with high-quality materials and cutting-edge technology. The 3 Series offers a smooth and powerful ride, thanks to its advanced engine and suspension system. It's equipped with the latest safety features, including adaptive cruise control, lane-keeping assist, and a state-of-the-art infotainment system. Whether you're driving in the city or on the highway, the BMW 3 Series delivers an exceptional driving experience.",
            "car1"),
        Car("Used", "Coupe", "USA", "Ford", "Mustang", 2020, "20,000 km", "$30,000",
            "The Ford Mustang is an iconic American muscle car that combines classic style with modern performance. This 2020 model boasts a powerful V8 engine that delivers exhilarating acceleration and a thrilling driving experience. The exterior features a bold and aggressive design, while the interior is equipped with comfortable seats and advanced technology. With its precise handling and responsive steering, the Mustang is a joy to drive on winding roads and open highways alike. This used model has been meticulously maintained and is in excellent condition, ready to provide years of driving pleasure.",
            "car2"),
        Car("New", "Hatchback", "Japan", "Toyota", "Yaris", 2022, "5 km", "$20,000",
            "The Toyota Yaris is a compact hatchback that offers a perfect blend of efficiency, reliability, and practicality. This 2022 model features a modern and stylish design, with a spacious interior that can comfortably accommodate passengers and cargo. The Yaris is powered by a fuel-efficient engine that delivers excellent mileage, making it an ideal choice for city driving and long commutes. Inside, you'll find a range of convenient features, including a user-friendly infotainment system, advanced safety technologies, and comfortable seating. The Yaris is known for its dependability and low maintenance costs, making it a smart investment for any driver.",
            "car3"),
        Car("Used", "Convertible", "Italy", "Ferrari", "488 Spider", 2019, "15,000 km", "$200,000",
            "The Ferrari 488 Spider is a high-performance convertible that embodies the spirit of Italian automotive excellence. This 2019 model features a stunning design with aerodynamic lines and a retractable hardtop that allows you to enjoy open-air driving at the push of a button. Under the hood, the 488 Spider is powered by a turbocharged V8 engine that delivers breathtaking speed and acceleration. The interior is crafted with the finest materials, offering a luxurious and comfortable driving environment. With its precise handling and advanced suspension system, the 488 Spider provides an exhilarating and dynamic driving experience. This used model is in pristine condition and ready to deliver the ultimate in driving pleasure.",
            "car4"),
        Car("New", "SUV", "Korea", "Hyundai", "Tucson", 2023, "10 km", "$35,000",
            "The Hyundai Tucson is a modern SUV that combines style, versatility, and advanced technology. This 2023 model features a bold and eye-catching design, with a spacious interior that offers ample room for passengers and cargo. The Tucson is equipped with a powerful and efficient engine, providing a smooth and responsive driving experience. Inside, you'll find a range of high-tech features, including a large touchscreen infotainment system, advanced safety technologies, and comfortable seating. Whether you're navigating city streets or exploring off-road trails, the Tucson delivers exceptional performance and comfort.",
            "car5"),
        Car("Used", "Sedan", "France", "Renault", "Megane", 2018, "30,000 km", "$15,000",
            "The Renault Megane is a reliable and practical sedan that offers a comfortable and enjoyable driving experience. This 2018 model features a sleek and modern design, with a spacious interior that provides plenty of room for passengers and cargo. The Megane is powered by an efficient engine that delivers good fuel economy, making it an ideal choice for daily commuting and long trips. Inside, you'll find a range of convenient features, including a user-friendly infotainment system, advanced safety technologies, and comfortable seating. This used model has been well-maintained and is in excellent condition, ready to provide years of dependable service.",
            "car6"),
        Car("New", "Sedan", "Germany", "Mercedes-Benz", "C-Class", 2023, "10 km", "$55,000",
            "The Mercedes-Benz C-Class is a luxury sedan that offers a perfect blend of elegance, performance, and advanced technology. This 2023 model features a sophisticated design with a spacious and luxurious interior. The C-Class is powered by a refined and powerful engine, providing a smooth and exhilarating driving experience. Inside, you'll find a range of high-tech features, including a large touchscreen infotainment system, advanced safety technologies, and premium materials. Whether you're driving in the city or on the highway, the C-Class delivers an exceptional level of comfort and performance.",
            "car7"),
        Car("Used", "Coupe", "USA", "Chevrolet", "Camaro", 2019, "25,000 km", "$28,000",
            "The Chevrolet Camaro is a sporty coupe that combines aggressive styling with powerful performance. This 2019 model features a bold and muscular design, with a powerful V8 engine that delivers thrilling acceleration and speed. The Camaro is equipped with advanced suspension and handling systems, providing a responsive and dynamic driving experience. Inside, you'll find a range of modern features, including a touchscreen infotainment system, advanced safety technologies, and comfortable seating. This used model has been well-maintained and is in excellent condition, ready to provide an exciting and enjoyable driving experience.",
            "car8"),
        Car("New", "Hatchback", "Japan", "Honda", "Civic", 2022, "5 km", "$22,000",
            "The Honda Civic is a popular hatchback that offers a perfect combination of style, efficiency, and practicality. This 2022 model features a sleek and modern design, with a spacious interior that provides plenty of room for passengers and cargo. The Civic is powered by a fuel-efficient engine that delivers excellent mileage, making it an ideal choice for city driving and long commutes. Inside, you'll find a range of convenient features, including a user-friendly infotainment system, advanced safety technologies, and comfortable seating. The Civic is known for its reliability and low maintenance costs, making it a smart investment for any driver.",
            "car9"),
        Car("Used", "Convertible", "Italy", "Lamborghini", "Huracan", 2018, "10,000 km", "$250,000",
            "The Lamborghini Huracan is a luxury sports convertible that offers an unparalleled driving experience. This 2018 model features a striking and aerodynamic design, with a powerful V10 engine that delivers breathtaking speed and performance. The Huracan is equipped with advanced suspension and handling systems, providing a precise and responsive driving experience. Inside, you'll find a luxurious and well-appointed cabin, with premium materials and cutting-edge technology. This used model is in pristine condition and ready to deliver the ultimate in driving pleasure.",
            "car10"),
        Car("New", "SUV", "Korea", "Kia", "Sportage", 2023, "10 km", "$30,000",
            "The Kia Sportage is a stylish and versatile SUV that offers a perfect combination of performance, comfort, and advanced technology. This 2023 model features a bold and eye-catching design, with a spacious interior that provides plenty of room for passengers and cargo. The Sportage is powered by a powerful and efficient engine, providing a smooth and responsive driving experience. Inside, you'll find a range of high-tech features, including a large touchscreen infotainment system, advanced safety technologies, and comfortable seating. Whether you're navigating city streets or exploring off-road trails, the Sportage delivers exceptional performance and comfort.",
            "car11"),
        Car("Used", "Sedan", "France", "Peugeot", "508", 2017, "40,000 km", "$18,000",
            "The Peugeot 508 is an elegant and sophisticated sedan that offers a comfortable and enjoyable driving experience. This 2017 model features a sleek and modern design, with a spacious interior that provides plenty of room for passengers and cargo. The 508 is powered by an efficient engine that delivers good fuel economy, making it an ideal choice for daily commuting and long trips. Inside, you'll find a range of convenient features, including a user-friendly infotainment system, advanced safety technologies, and comfortable seating. This used model has been well-maintained and is in excellent condition, ready to provide years of dependable service.",
            "car12"),
        Car("New", "Sedan", "Germany", "Audi", "A4", 2023, "10 km", "$52,000",
            "The Audi A4 is a premium sedan that offers a perfect blend of luxury, performance, and advanced technology. This 2023 model features a sophisticated design with a spacious and luxurious interior. The A4 is powered by a refined and powerful engine, providing a smooth and exhilarating driving experience. Inside, you'll find a range of high-tech features, including a large touchscreen infotainment system, advanced safety technologies, and premium materials. Whether you're driving in the city or on the highway, the A4 delivers an exceptional level of comfort and performance.",
            "car13"),
        Car("Used", "Coupe", "USA", "Dodge", "Challenger", 2019, "20,000 km", "$32,000",
            "The Dodge Challenger is a powerful coupe that combines classic muscle car styling with modern performance. This 2019 model features a bold and aggressive design, with a powerful V8 engine that delivers thrilling acceleration and speed. The Challenger is equipped with advanced suspension and handling systems, providing a responsive and dynamic driving experience. Inside, you'll find a range of modern features, including a touchscreen infotainment system, advanced safety technologies, and comfortable seating. This used model has been well-maintained and is in excellent condition, ready to provide an exciting and enjoyable driving experience.",
            "car14"),
        Car("New", "Hatchback", "Japan", "Nissan", "Leaf", 2022, "5 km", "$25,000",
            "The Nissan Leaf is an electric hatchback that offers a perfect combination of efficiency, practicality, and advanced technology. This 2022 model features a sleek and modern design, with a spacious interior that provides plenty of room for passengers and cargo. The Leaf is powered by an electric motor that delivers excellent mileage, making it an ideal choice for city driving and long commutes. Inside, you'll find a range of convenient features, including a user-friendly infotainment system, advanced safety technologies, and comfortable seating. The Leaf is known for its reliability and low maintenance costs, making it a smart investment for any driver.",
            "car15"),
        Car("Used", "Convertible", "Italy", "Maserati", "GranTurismo", 2018, "15,000 km", "$150,000",
            "The Maserati GranTurismo is a luxurious convertible that offers an unparalleled driving experience. This 2018 model features a striking and aerodynamic design, with a powerful V8 engine that delivers breathtaking speed and performance. The GranTurismo is equipped with advanced suspension and handling systems, providing a precise and responsive driving experience. Inside, you'll find a luxurious and well-appointed cabin, with premium materials and cutting-edge technology. This used model is in pristine condition and ready to deliver the ultimate in driving pleasure.",
            "car16")
    )


    private val savedCars = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusSpinner = findViewById(R.id.statusSpinner)
        typeSpinner = findViewById(R.id.typeSpinner)
        countrySpinner = findViewById(R.id.countrySpinner)
        showSavedCarsButton = findViewById(R.id.showSavedCarsButton)
        carRecyclerView = findViewById(R.id.carRecyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        carRecyclerView.layoutManager = LinearLayoutManager(this)
        carAdapter = CarAdapter(this, cars) { car ->
            val intent = Intent(this, CarDetailActivity::class.java).apply {
                putExtra("carStatus", car.status)
                putExtra("carType", car.type)
                putExtra("carCountry", car.country)
                putExtra("carBrand", car.brand)
                putExtra("carModel", car.model)
                putExtra("carYear", car.year)
                putExtra("carMileage", car.mileage)
                putExtra("carPrice", car.price)
                putExtra("carDescription", car.description)
                putExtra("carImageUri", car.imageUri)
            }
            startActivityForResult(intent, REQUEST_CODE_SAVE_CAR)
        }
        carRecyclerView.adapter = carAdapter

        statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterCars()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterCars()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterCars()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        searchButton.setOnClickListener {
            filterCars()
            hideKeyboard()
        }

        showSavedCarsButton.setOnClickListener {
            val intent = Intent(this, SavedCarsActivity::class.java)
            intent.putParcelableArrayListExtra("savedCars", ArrayList(savedCars))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SAVE_CAR && resultCode == RESULT_OK) {
            val savedCar = data?.getParcelableExtra<Car>("savedCar")
            savedCar?.let {
                savedCars.add(it)
            }
        }
    }

    private fun filterCars() {
        val status = statusSpinner.selectedItem.toString()
        val type = typeSpinner.selectedItem.toString()
        val country = countrySpinner.selectedItem.toString()
        val searchQuery = searchEditText.text.toString().trim()

        val filteredCars = cars.filter { car ->
            (status == "All" || car.status == status) &&
                    (type == "All" || car.type == type) &&
                    (country == "All" || car.country == country) &&
                    (searchQuery.isEmpty() || car.model.contains(searchQuery, ignoreCase = true))
        }
        carAdapter.updateCars(filteredCars)
    }

    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {
        private const val REQUEST_CODE_SAVE_CAR = 1
    }

}
