package com.mustafasuleymankinik.spacetraveler.repo.network
import com.mustafasuleymankinik.spacetraveler.model.Planet
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
interface Api {
    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    fun getList():Observable<List<Planet>>
}