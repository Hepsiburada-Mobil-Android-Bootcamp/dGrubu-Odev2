package com.hepsiburada.dgrubuodev2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hepsiburada.dgrubuodev2.data.model.NearestRestaurant
import com.hepsiburada.dgrubuodev2.databinding.ItemNearestRestaurantBinding


class NearestRestaurantAdapter(private val nearestRestaurantList: MutableList<NearestRestaurant>) :
    RecyclerView.Adapter<NearestRestaurantAdapter.NearestRestaurantVH>() {

    class NearestRestaurantVH(private val binding: ItemNearestRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NearestRestaurant) {
            binding.ivRestaurantLogo.setImageResource(item.restaurantLogo)
            binding.tvRestaurantName.text = item.restaurantName
            binding.tvDistanceTime.text = item.restaurantDistanceTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearestRestaurantVH {
        val binding = ItemNearestRestaurantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NearestRestaurantVH(binding)
    }

    override fun onBindViewHolder(holder: NearestRestaurantVH, position: Int) {
        holder.bind(nearestRestaurantList[position])
    }

    override fun getItemCount(): Int = nearestRestaurantList.size
}
