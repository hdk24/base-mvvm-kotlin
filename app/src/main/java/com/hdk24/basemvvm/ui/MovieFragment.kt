package com.hdk24.basemvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.databinding.FragmentMovieBinding
import com.hdk24.basemvvm.domain.exception.Failure
import com.hdk24.basemvvm.presentation.base.BaseFragment
import com.hdk24.basemvvm.presentation.common.LoadingState
import com.hdk24.basemvvm.presentation.common.ResultState
import com.hdk24.basemvvm.presentation.model.Movie
import com.hdk24.basemvvm.presentation.viewModel.MovieViewModel
import com.hdk24.basemvvm.ui.adapter.MovieAdapter
import com.hdk24.basemvvm.ui.widget.GridSpaceDecoration
import com.hdk24.basemvvm.utils.AppLogger

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {

    private val adapter = MovieAdapter()

    override fun getViewModelClass(): Class<MovieViewModel> = MovieViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_movie

    override fun onViewReady(savedInstance: Bundle?) {
        initViews()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        viewModel.fetchMovie(1)
        viewModel.movie.observe(viewLifecycleOwner, Observer { handleResult(it) })
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerMovie.layoutManager = gridLayoutManager
        binding.recyclerMovie.addItemDecoration(GridSpaceDecoration(2, 16))
        binding.recyclerMovie.adapter = adapter

        binding.loadingView.onRefreshListener = {
            Snackbar.make(binding.root, "Refreshing...", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleResult(state: ResultState<List<Movie>>) {
        when (state) {
            is ResultState.OnLoading -> showLoading()
            is ResultState.OnSuccess -> showData(state.data)
            is ResultState.OnError -> handleError(state.error)
        }
    }

    private fun showLoading() {
        binding.loadingState = LoadingState.OnLoading
        binding.showList = false
    }

    private fun showData(movieList: List<Movie>) {
        AppLogger.d("Hdk movie result stats ${movieList.size}")
        adapter.submitList(movieList)
        binding.loadingState = LoadingState.OnFinish
        binding.showList = true
    }

    private fun handleError(error: Failure) {
        binding.showList = false
        binding.loadingState = LoadingState.OnError(error.code)
    }
}
