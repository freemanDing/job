package com.hilton.job.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.hilton.job.BuildConfig
import com.hilton.job.R
import com.hilton.job.common.Contacts.DEV_BASE_URL
import okhttp3.OkHttpClient

class TestActivity : AppCompatActivity() {

    private lateinit var client: ApolloClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        request()
    }

    private fun request() {
        client = setupApollo()
//        client.query(
//            FindQuery
//                .builder()
//                .name("")
//                .owner("")
//                .build()
//        )
//            .enqueue(object : ApolloCall.Callback<FindQuery.Data>() {
//
//                override fun onFailure(e: ApolloException) {
//                    Log.i("onFailure", e.message.toString())
//                }
//
//                override fun onResponse(response: Response<FindQuery.Data>) {
//                    Log.i("onResponse", " " + response.data()?.repository())
//                }
//
//            })
    }

    private fun setupApollo(): ApolloClient {
        val okHttp = OkHttpClient
            .Builder()
            .addInterceptor({ chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(
                    original.method(),
                    original.body()
                )
//                builder.addHeader(
//                    "Authorization", "Bearer " + BuildConfig.AUTH_TOKEN
//                )
                chain.proceed(builder.build())
            })
            .build()
        return ApolloClient.builder()
            .serverUrl(DEV_BASE_URL)
            .okHttpClient(okHttp)
            .build()
    }
}