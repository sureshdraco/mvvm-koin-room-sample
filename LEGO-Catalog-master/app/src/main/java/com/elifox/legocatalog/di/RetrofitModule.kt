package com.elifox.legocatalog.di

import com.elifox.legocatalog.BuildConfig
import com.elifox.legocatalog.api.AuthInterceptor
import com.elifox.legocatalog.api.LegoService
import com.elifox.legocatalog.data.AppDatabase
import com.elifox.legocatalog.legoset.data.LegoSetRemoteDataSource
import com.elifox.legocatalog.legotheme.data.LegoThemeRemoteDataSource
import com.elifox.legocatalog.legotheme.data.LegoThemeRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }
    }

    single {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
                .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN))
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    single {
        get<AppDatabase>().legoSetDao()
    }

    single {
        get<AppDatabase>().legoThemeDao()
    }

    single {
        AppDatabase.getInstance(get())
    }

    single {
        createRetrofit(get(), get())
    }

    single {
        provideService(get(), get(), LegoService::class.java)
    }

    single {
        Gson()
    }

    single {
        GsonConverterFactory.create(get())
    }
}

private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
            .baseUrl(LegoService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
}

private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
): T {
    return createRetrofit(okhttpClient, converterFactory).create(clazz)
}