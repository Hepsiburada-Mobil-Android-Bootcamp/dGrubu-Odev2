package com.hepsiburada.dgrubuodev2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.FoodCardBinding

class FoodsAdapter(var mContext: Context, var foodList:List<Foods>)
    :RecyclerView.Adapter<FoodsAdapter.CardDesignHolder>() {


    inner class CardDesignHolder(foodCardBinding: FoodCardBinding)
        : RecyclerView.ViewHolder(foodCardBinding.root){

        var design: FoodCardBinding

        init{
            this.design=foodCardBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design=FoodCardBinding.inflate(layoutInflater,parent,false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {

        val food=foodList.get(position)
        holder.design.foodObject=food

/*
        holder.design.textViewCardFoodPrice.text = food.yemek_fiyat.toString()+".00 \u20BA"

        holder.design.foodCardview.setOnClickListener {
            //var nameObject= food.yemek_adi
            //var priceObject= food.yemek_fiyat.toString()
            val pass=HomePageFragmentDirections.transitionHomePageToFoodDetailsPage(food)
            Navigation.findNavController(it).navigate(pass)   */
        }

    override fun getItemCount(): Int {
        return foodList.size
    }
}
