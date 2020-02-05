package com.elifox.legocatalog.legoset.ui

import androidx.lifecycle.ViewModel
import com.elifox.legocatalog.legoset.data.LegoSetRepository

/**
 * The ViewModel used in [LegoSetFragment].
 */
class LegoSetViewModel constructor(repository: LegoSetRepository) : ViewModel() {

    lateinit var id: String

    val legoSet by lazy { repository.observeSet(id) }

}
