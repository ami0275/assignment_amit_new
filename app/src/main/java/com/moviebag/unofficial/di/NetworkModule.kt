package com.moviebag.unofficial.di

import android.app.Application
import com.moviebag.unofficial.base.di.ApiInterceptor
import com.moviebag.unofficial.data.service.RemoteApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.moviebag.unofficial.BuildConfig
import com.moviebag.unofficial.util.DateDeserializer
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(55.toLong(), TimeUnit.SECONDS)
        .connectTimeout(55.toLong(), TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(Date::class.java, DateDeserializer()).create())

    @Provides
    @Singleton
    fun provideApi(application: Application, builder: Retrofit.Builder, okHttpClientBuilder: OkHttpClient.Builder, converterFactory: Converter.Factory, interceptor: ApiInterceptor): RemoteApiService {
        /*if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
            okHttpClientBuilder.addInterceptor(ChuckInterceptor(application))
        }*/
        okHttpClientBuilder.addInterceptor(interceptor)
        val client = okHttpClientBuilder.build()
        return builder.client(client)
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(converterFactory)
            .build()
            .create(RemoteApiService::class.java)
    }

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
            object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
    }
}