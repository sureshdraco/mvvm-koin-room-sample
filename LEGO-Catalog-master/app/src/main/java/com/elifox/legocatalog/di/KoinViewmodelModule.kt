package com.elifox.legocatalog.di

import com.elifox.legocatalog.legoset.data.LegoSetRemoteDataSource
import com.elifox.legocatalog.legoset.ui.LegoSetViewModel
import com.elifox.legocatalog.legoset.ui.LegoSetsViewModel
import com.elifox.legocatalog.legotheme.data.LegoThemeRemoteDataSource
import com.elifox.legocatalog.legotheme.data.LegoThemeRepository
import com.elifox.legocatalog.legotheme.ui.LegoThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodeModule = module {

    viewModel {
        LegoThemeViewModel(get())
    }
    viewModel {
        LegoSetsViewModel(get())
    }
    viewModel {
        LegoSetViewModel(get())
    }
}