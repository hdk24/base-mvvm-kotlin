package com.hdk24.basemvvm.presentation.common

sealed class LoadingState {
    object OnLoading : LoadingState()
    object OnFinish : LoadingState()
    data class OnError(val code: Int) : LoadingState()
}