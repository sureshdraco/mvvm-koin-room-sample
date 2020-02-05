package com.elifox.legocatalog.di

import com.elifox.legocatalog.legoset.data.LegoSetRemoteDataSource
import com.elifox.legocatalog.legoset.data.LegoSetRepository
import com.elifox.legocatalog.legotheme.data.LegoThemeRemoteDataSource
import com.elifox.legocatalog.legotheme.data.LegoThemeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        LegoSetRemoteDataSource(get())
    }

    single {
        LegoThemeRemoteDataSource(get())
    }

    single {
        LegoThemeRepository(get(), get())
    }

    single {
        LegoSetRepository(get(), get())
    }
}