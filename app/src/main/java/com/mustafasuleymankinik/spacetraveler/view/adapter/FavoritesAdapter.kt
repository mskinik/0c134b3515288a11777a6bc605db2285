package com.mustafasuleymankinik.spacetraveler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.LayoutFavoritesAdapterBinding
import com.mustafasuleymankinik.spacetraveler.model.Planet

/**
 * Created by mustafasuleymankinik on 26.02.2022.
 */
class FavoritesAdapter(val list: List<Planet>,val itemClickFavoriteCallback:((Long,Boolean)->Unit)?):RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val binding = LayoutFavoritesAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = list[position]
        val binding = holder.binding
        binding.apply {
            if (item.favorite){
                item.name?.let {
                    tvName.text = it
                }
                item.distanceToEarth?.let {
                    tvDistanceToEarth.text = context.getString(R.string.distance_to_earth,it.toInt().toString())
                }
                ivStar.setOnClickListener {
                    itemClickFavoriteCallback?.invoke(item.id,item.favorite)
                }
                if (item.travelled)
                    tvTravelled.visibility = View.VISIBLE
                else
                    tvTravelled.visibility = View.GONE
                if (item.capacity != null && item.stock != null)
                    tvStock.text = "${item.capacity}/${item.stock}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: LayoutFavoritesAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }
}