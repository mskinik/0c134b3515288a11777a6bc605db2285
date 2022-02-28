package com.mustafasuleymankinik.spacetraveler.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafasuleymankinik.spacetraveler.base.BaseFragment
import com.mustafasuleymankinik.spacetraveler.databinding.FragmentMainBinding
import com.mustafasuleymankinik.spacetraveler.model.Planet
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.view.adapter.PlanetAdapter
import com.mustafasuleymankinik.spacetraveler.viewmodel.MainFragmentViewModel


class MainFragment : BaseFragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    var planetAdapter: PlanetAdapter? = null
    var planetList = mutableListOf<Planet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this@MainFragment,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(MainFragmentViewModel::class.java)
        binding.apply {
            mainFrag = this@MainFragment
            mainViewModel = viewModel
            viewModel.user.observe(viewLifecycleOwner, Observer {
                user = it
            })
            rvList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewModel.planetList.observe(viewLifecycleOwner, Observer {
                planetList.clear()
                planetList.addAll(it)

                planetAdapter = PlanetAdapter(
                    planetList,
                    itemClickTravelCallback = fun(id: Long, planet: Planet) {
                        travelClick(id, planet)
                    },
                    itemClickFavoriteCallback = fun(id: Long, favorite: Boolean) {
                        viewModel.updatePlanetFavorite(id, favorite)
                    })
                rvList.adapter = planetAdapter
            })
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    planetAdapter?.filter?.filter(newText);
                    return false
                }
            })
            viewModel.mappedUserLiveData.observe(viewLifecycleOwner, Observer {
                if (it)
                    clFinishHolder.visibility = View.VISIBLE
            })
        }
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                showLoading()
            else
                hideLoading()
        })
    }

    fun leftClick() {
        val layoutManager = binding.rvList.layoutManager
        (layoutManager as LinearLayoutManager).scrollToPosition(layoutManager.findFirstVisibleItemPosition() - 1)
    }

    fun rightClick() {
        val layoutManager = binding.rvList.layoutManager
        (layoutManager as LinearLayoutManager).scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1)
    }

    fun travelClick(id: Long, planet: Planet) {
        viewModel.travel(id, planet)
    }
}