package com.mustafasuleymankinik.spacetraveler.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.FragmentFavoritesBinding
import com.mustafasuleymankinik.spacetraveler.view.adapter.FavoritesAdapter
import com.mustafasuleymankinik.spacetraveler.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this@FavoritesFragment,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(FavoritesViewModel::class.java)
        binding.apply {
            rvFavorites.layoutManager = LinearLayoutManager(context)
            viewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
                rvFavorites.adapter = FavoritesAdapter(it, itemClickFavoriteCallback = fun(id:Long,favorite:Boolean){
                    viewModel.updateFavorite(id,favorite)
                })
            })

        }
    }

}