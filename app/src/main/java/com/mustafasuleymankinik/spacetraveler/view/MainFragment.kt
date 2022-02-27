package com.mustafasuleymankinik.spacetraveler.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafasuleymankinik.spacetraveler.R
import com.mustafasuleymankinik.spacetraveler.databinding.FragmentMainBinding
import com.mustafasuleymankinik.spacetraveler.repo.database.Dao
import com.mustafasuleymankinik.spacetraveler.view.adapter.PlanetAdapter
import com.mustafasuleymankinik.spacetraveler.viewmodel.MainFragmentViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var dao: Dao
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
            rvList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewModel.planetList.observe(viewLifecycleOwner, Observer {
                rvList.adapter = PlanetAdapter(it, itemClickTravelCallback = fun(id: Long) {

                }, itemClickFavoriteCallback = fun(id: Long, favorite: Boolean) {
                    viewModel.updatePlanetFavorite(id, favorite)
                })
            })

        }
    }

    fun leftClick() {
        val layoutManager = binding.rvList.layoutManager
        (layoutManager as LinearLayoutManager).scrollToPosition(layoutManager.findFirstVisibleItemPosition() - 1)
    }

    fun rightClick() {
        val layoutManager = binding.rvList.layoutManager
        (layoutManager as LinearLayoutManager).scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1)
    }
}