package com.junaid.todoapp.utils.app


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class ToDoApp : Application() {
    companion object {
        var isNetworkConnected = false
    }

    override fun onCreate() {
        super.onCreate()
        startNetworkStateMonitoring()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }



    private fun startNetworkStateMonitoring() {
        val cm = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(networkCallBack)
        } else {
            cm.registerNetworkCallback(builder.build(), networkCallBack)
        }
    }

    private val networkCallBack = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isNetworkConnected = true
        }

        override fun onLost(network: Network) {
            isNetworkConnected = false
        }
    }

}