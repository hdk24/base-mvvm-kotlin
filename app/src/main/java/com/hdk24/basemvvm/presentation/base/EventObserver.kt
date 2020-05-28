package com.hdk24.basemvvm.presentation.base

import androidx.lifecycle.Observer

/**
 * An [Observer] for [SingleEvents]s, simplifying the pattern of checking if the [SingleEvents]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [SingleEvents]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvents<T>> {
    override fun onChanged(event: SingleEvents<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
