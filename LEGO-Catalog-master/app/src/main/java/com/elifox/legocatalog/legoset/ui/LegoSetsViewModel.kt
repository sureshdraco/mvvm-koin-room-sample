package com.elifox.legocatalog.legoset.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elifox.legocatalog.legoset.data.LegoSetRepository
import kotlinx.coroutines.cancel

/**
 * The ViewModel for [LegoSetsFragment].
 */
class LegoSetsViewModel constructor(private val repository: LegoSetRepository) : ViewModel() {

    var connectivityAvailable: Boolean = false
    var themeId: Int? = null

    val legoSets by lazy {
        repository.observePagedSets(
                connectivityAvailable, themeId, viewModelScope
        )
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
