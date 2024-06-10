package com.example.autoshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autoshow.R

class CarAdapter(private val context: Context, private var cars: List<Car>, private val onItemClicked: (Car) -> Unit) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int = cars.size

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        private val carBrandTextView: TextView = itemView.findViewById(R.id.carBrandTextView)
        private val carModelTextView: TextView = itemView.findViewById(R.id.carModelTextView)
        private val carYearTextView: TextView = itemView.findViewById(R.id.carYearTextView)
        private val carTypeTextView: TextView = itemView.findViewById(R.id.carTypeTextView)
        private val carMileageTextView: TextView = itemView.findViewById(R.id.carMileageTextView)
        private val carPriceTextView: TextView = itemView.findViewById(R.id.carPriceTextView)
        private val carDescriptionTextView: TextView = itemView.findViewById(R.id.carDescriptionTextView)

        fun bind(car: Car) {
            carBrandTextView.text = car.brand
            carModelTextView.text = car.model
            carYearTextView.text = "Year: ${car.year}"
            carTypeTextView.text = "Type: ${car.type}"
            carMileageTextView.text = "Mileage: ${car.mileage}"
            carPriceTextView.text = "Price: ${car.price}"
            carDescriptionTextView.text = "Description: ${car.description}"

            val imageResource = itemView.context.resources.getIdentifier(car.imageUri, "drawable", itemView.context.packageName)
            Glide.with(itemView.context)
                .load(imageResource)
                .into(carImageView)

            itemView.setOnClickListener {
                onItemClicked(car)
            }
        }
    }

    fun updateCars(newCars: List<Car>) {
        cars = newCars
        notifyDataSetChanged()
    }
}
