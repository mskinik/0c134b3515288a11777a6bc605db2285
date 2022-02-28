package com.mustafasuleymankinik.spacetraveler.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.LayoutPlanetAdapterBinding
import com.mustafasuleymankinik.spacetraveler.model.Planet

/**
 * Created by mustafasuleymankinik on 26.02.2022.
 */
class PlanetAdapter(
    val list: MutableList<Planet>,
    val itemClickTravelCallback: ((Long,Planet) -> Unit)?,
    val itemClickFavoriteCallback: ((Long, Boolean) -> Unit)?
) : RecyclerView.Adapter<PlanetAdapter.ViewHolder>(), Filterable {
    var filteredPlanetList: MutableList<Planet> = list
    var listPlanet: MutableList<Planet> = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetAdapter.ViewHolder {
        val binding =
            LayoutPlanetAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = filteredPlanetList[position]
        val binding = holder.binding
        binding.apply {
            item.name?.let {
                tvName.text = it
            }
            ivStar.setOnClickListener {
                itemClickFavoriteCallback?.invoke(item.id, item.favorite)
            }
            if (item.travelled){
                clHolderTravel.visibility = View.VISIBLE
                btTravel.visibility = View.INVISIBLE
            }
            else{
                clHolderTravel.visibility = View.GONE
                btTravel.visibility = View.VISIBLE
            }

            if (item.favorite){
                ivStar.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_star_yellow_48))
            }
            else{
                ivStar.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_star_border_48))
        }
            item.distanceToEarth?.let {
                tvDistanceToEarth.text = context.getString(R.string.distance_to_earth,it.toInt().toString())
            }
            if (item.capacity != null && item.stock != null)
                tvStock.text = "${item.capacity}/${item.stock}"
            btTravel.setOnClickListener { itemClickTravelCallback?.invoke(item.id, item) }
        }

    }

    override fun getItemCount(): Int {
        return filteredPlanetList.size
    }

    class ViewHolder(val binding: LayoutPlanetAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val searchString: String = constraint.toString().lowercase()

                if (searchString.isEmpty()) {
                    filteredPlanetList.clear()
                    filteredPlanetList.addAll(list)
                } else {
                    val tempFilteredList = mutableListOf<Planet>()
                    for (planet: Planet in list) {
                        if (planet.name?.lowercase()?.contains(searchString) == true) {
                            tempFilteredList.add(planet)
                        }
                    }
                    filteredPlanetList = tempFilteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredPlanetList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredPlanetList = results.values as MutableList<Planet>
                notifyDataSetChanged()
            }
        }

    }
}