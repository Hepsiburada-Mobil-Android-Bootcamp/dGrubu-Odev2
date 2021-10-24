package com.hepsiburada.dgrubuodev2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.ItemFoodBinding

class HomeFragmentAdapter(val foodList:ArrayList<Foods>,val firebaseIdList:ArrayList<String>):RecyclerView.Adapter<HomeFragmentAdapter.FoodHolder>() {
    class FoodHolder(val binding:ItemFoodBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding=ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.foodCalorieText.text=foodList[position].foodCalory.toString()
        holder.binding.foodCookingTimeText.text=foodList[position].foodCookingTime.toString()
        holder.binding.foodNameText.text=foodList[position].foodName
        holder.binding.foodCategoryText.text=foodList[position].foodCategory

        holder.itemView.setOnClickListener{

        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}