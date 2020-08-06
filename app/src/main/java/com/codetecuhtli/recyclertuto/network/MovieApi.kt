package com.codetecuhtli.recyclertuto.network

import com.codetecuhtli.recyclertuto.model.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movies")
    suspend fun getMovies(): List<Movie>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: Int): Movie

    companion object {

        private var backedApi: MovieApi? = null

        val instance: MovieApi by lazy {
            if (backedApi == null){
                backedApi = createApi()
                backedApi!!
            }else {
                backedApi!!
            }
        }

        private fun createApi(): MovieApi {
            //Interceptor
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            //Client
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            //Retrofit
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://9075f3103ac5.ngrok.io/") //No es la mejor forma de hacerlo
                .build()

            return retrofit.create(MovieApi::class.java)
        }


    }

}