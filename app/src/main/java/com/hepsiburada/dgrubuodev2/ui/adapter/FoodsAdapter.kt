package com.hepsiburada.dgrubuodev2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.ItemFoodBinding

class FoodsAdapter(options: FirestoreRecyclerOptions<Foods>,
) : FirestoreRecyclerAdapter<Foods, FoodsAdapter.FoodHolder>(options) {

    class FoodHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: Foods
        ) { binding.apply {
            foodNameText.setText(model.foodName)
            foodCalorieText.setText(model.foodCalory.toString())
            foodCategoryText.setText(model.foodCategory)
            foodCookingTimeText.setText(model.foodCookingTime.toString())
        }

        }
        companion object {
            fun from(parent: ViewGroup): FoodHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFoodBinding.inflate(layoutInflater,parent, false)
                return FoodHolder(binding)
            }
        }
    }

    fun deleteItem(position: Int) {
        snapshots.getSnapshot(position).reference.delete()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int, model: Foods) {
        holder.bind(model)
    }
}