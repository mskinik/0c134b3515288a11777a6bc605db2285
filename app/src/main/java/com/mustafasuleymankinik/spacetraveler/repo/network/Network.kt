package com.mustafasuleymankinik.spacetraveler.repo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mustafasuleymankinik on 27.02.2022.
 */
class NetworkService {
    companion object {
        private var api: Api? = null
        private val BASE_URL = "https://run.mocky.io/v3/"

        fun getApi():Api{
            if (api==null)
                api = getRetrofit(BASE_URL).create(Api::class.java)
            return api as Api
        }


        fun getRetrofit(url:String): Retrofit {
            val builder = OkHttpClient.Builder()
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)

            return Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}