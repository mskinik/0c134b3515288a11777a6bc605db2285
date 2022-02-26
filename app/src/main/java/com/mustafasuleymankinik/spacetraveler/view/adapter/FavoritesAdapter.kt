package com.mustafasuleymankinik.spacetraveler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafasuleymankinik.spacetraveler.databinding.LayoutFavoritesAdapterBinding

/**
 * Created by mustafasuleymankinik on 26.02.2022.
 */
class FavoritesAdapter():RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val binding = LayoutFavoritesAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(binding: LayoutFavoritesAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }
}