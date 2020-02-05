package com.elifox.legocatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.elifox.legocatalog.data.Result.Status.ERROR
import com.elifox.legocatalog.data.Result.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T> resultLiveData(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<T>())
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == SUCCESS) {
                emit(Result.success(responseStatus.data!!))
            } else if (responseStatus.status == ERROR) {
                emit(Result.error<T>(responseStatus.message!!))
            }
        }