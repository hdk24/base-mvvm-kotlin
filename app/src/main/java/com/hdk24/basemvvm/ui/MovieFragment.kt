package com.hdk24.basemvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.databinding.FragmentMovieBinding
import com.hdk24.basemvvm.domain.exception.Failure
import com.hdk24.basemvvm.presentation.base.BaseFragment
import com.hdk24.basemvvm.presentation.common.LoadingState
import com.hdk24.basemvvm.presentation.common.ResultState
import com.hdk24.basemvvm.presentation.viewModel.MovieViewModel
import com.hdk24.basemvvm.ui.adapter.MovieAdapter
import com.hdk24.basemvvm.ui.widget.GridSpaceDecoration
import com.hdk24.basemvvm.utils.AppLogger
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {

    private lateinit var adapter: MovieAdapter

    private var newLoaded = true

    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_movie

    override fun onViewReady(savedInstance: Bundle?) {
        initViews()
        viewModel.fetchMovie()
        initAdapter()
        initViewState()
    }

    private fun onRefresh() {
        newLoaded = true
        viewModel.refresh()
    }

    private fun initViews() {
        binding.swipeRefresh.setOnRefreshListener { viewModel.refresh() }
        binding.loadingView.onRefreshListener = { onRefresh() }
    }

    private fun initAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerMovie.addItemDecoration(GridSpaceDecoration(2, 16))
        binding.recyclerMovie.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = spanGridLayout
        adapter = MovieAdapter { viewModel.retry() }
        binding.recyclerMovie.adapter = adapter
        viewModel.movieList.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }

    private fun initViewState() {
        viewModel.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultState.OnLoading -> showLoading()
                is ResultState.OnSuccess -> finishLoading()
                is ResultState.OnError -> handleError(it.error)
            }
        })
    }

    private val spanGridLayout = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (adapter.getItemViewType(position)) {
                MovieAdapter.VIEW_TYPE_ITEM -> 1
                MovieAdapter.VIEW_TYPE_FOOTER -> 2
                else -> -1
            }
        }
    }

    private fun showLoading() {
        val firstLoad = viewModel.listIsEmpty() && newLoaded
        val state = LoadingState.OnLoading
        if (firstLoad) binding.loadingState = state
        else adapter.setState(state)
        binding.showList = !firstLoad
        AppLogger.d("Hdk movie state loading : $firstLoad")
    }

    private fun finishLoading() {
        AppLogger.d("Hdk movie state finishLoading")
        newLoaded = false
        binding.showList = true
        adapter.setState(LoadingState.OnFinish)
        binding.loadingState = LoadingState.OnFinish
        swipeRefresh.isRefreshing = false
    }

    private fun handleError(error: Failure) {
        AppLogger.d("Hdk movie state error ${error.msg}")
        val firstLoad = viewModel.listIsEmpty() && newLoaded
        val state = LoadingState.OnError(error.code)
        if (firstLoad) binding.loadingState = state
        else adapter.setState(state)
        binding.showList = !firstLoad
    }
}
