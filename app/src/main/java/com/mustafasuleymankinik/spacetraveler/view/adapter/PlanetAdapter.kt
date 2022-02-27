package com.mustafasuleymankinik.spacetraveler.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.LayoutPlanetAdapterBinding
import com.mustafasuleymankinik.spacetraveler.model.Planet

/**
 * Created by mustafasuleymankinik on 26.02.2022.
 */
class PlanetAdapter(
    val list: List<Planet>,
    val itemClickTravelCallback: ((Long) -> Unit)?,
    val itemClickFavoriteCallback: ((Boolean, Long) -> Unit)?
) : RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetAdapter.ViewHolder {
        val binding =
            LayoutPlanetAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = list[position]
        val binding = holder.binding
        binding.apply {
            item.name?.let {
                tvName.text = it
            }
            ivStar.setOnClickListener {
                itemClickFavoriteCallback?.invoke(item.favorite, item.id)
            }
            if (item.favorite)
                ivStar.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_star_yellow_48)
            else
                ivStar.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_star_border_48)
            item.eus?.let {
                tvPlanetEus.text = it
            }
            if (item.capacity != null && item.stock != null)
                tvStock.text = "${item.capacity}/${item.stock}"
            btTravel.setOnClickListener { itemClickTravelCallback?.invoke(item.id) }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: LayoutPlanetAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}