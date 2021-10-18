package com.hepsiburada.dgrubuodev2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hepsiburada.dgrubuodev2.databinding.ItemPopularMenuBinding
import com.hepsiburada.dgrubuodev2.data.model.PopularMenu


class PopularMenuAdapter(private val menuList: MutableList<PopularMenu>) :
    RecyclerView.Adapter<PopularMenuAdapter.PopularMenuVH>() {

    class PopularMenuVH(private val binding: ItemPopularMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularMenu) {
            binding.ivMenu.setImageResource(item.menuImage)
            binding.tvMenuName1.text = item.menuTitle
            binding.tvMenuName2.text = item.menuSubTitle
            binding.tvMenuPrice.text = item.menuPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMenuVH {
        val binding = ItemPopularMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularMenuVH(binding)
    }

    override fun onBindViewHolder(holder: PopularMenuVH, position: Int) {
        holder.bind(menuList[position])
    }

    override fun getItemCount(): Int = menuList.size
}
