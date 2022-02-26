package com.mustafasuleymankinik.spacetraveler.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafasuleymankinik.spacetraveler.databinding.LayoutPlanetAdapterBinding

/**
 * Created by mustafasuleymankinik on 26.02.2022.
 */
class PlanetAdapter():RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetAdapter.ViewHolder {
        val binding = LayoutPlanetAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(binding: LayoutPlanetAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }
}