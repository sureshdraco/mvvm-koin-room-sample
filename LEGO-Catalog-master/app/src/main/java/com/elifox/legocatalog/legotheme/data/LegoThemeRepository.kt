package com.elifox.legocatalog.legotheme.data

import com.elifox.legocatalog.data.resultLiveData

/**
 * Repository module for handling data operations.
 */
class LegoThemeRepository constructor(private val dao: LegoThemeDao,
                                              private val remoteSource: LegoThemeRemoteDataSource) {

    val themes = resultLiveData(
            networkCall = { remoteSource.fetchData() })

}
