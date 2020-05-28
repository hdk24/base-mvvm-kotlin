package com.hdk24.basemvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.hdk24.basemvp.presentation.exception.Status
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.databinding.FragmentMovieBinding
import com.hdk24.basemvvm.presentation.base.BaseFragment
import com.hdk24.basemvvm.presentation.exception.NetworkState
import com.hdk24.basemvvm.presentation.model.Movie
import com.hdk24.basemvvm.presentation.viewModel.MovieViewModel
import com.hdk24.basemvvm.ui.adapter.MovieAdapter
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
        viewModel.movie.observe(viewLifecycleOwner, Observer { showData(it) })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { handleError(it) })
    }

    private fun initViews() {
        binding.recyclerMovie.adapter = adapter
    }

    private fun showData(movieList: List<Movie>) {
        AppLogger.d("Hdk movie result stats ${movieList.size}")
        adapter.submitList(movieList)
    }

    private fun handleError(error: NetworkState) {
        AppLogger.d("Hdk network state : ${error.status}")
        when (error.status) {
            Status.FAILED -> {
                binding.showLoading = false
                Snackbar.make(binding.root, error.failure?.msg ?: "ERROR", Snackbar.LENGTH_LONG)
                    .show()
            }
            Status.LOADING -> binding.showLoading = true
            Status.SUCCESS -> binding.showLoading = false
        }
    }
}
