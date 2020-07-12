package com.candidate.android.dev.wongnai_assignment.Extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import java.lang.Exception


fun Any.simpleName(): String = this::class.java.simpleName

fun Context.checkInternet():Boolean {
    var isConnected = false
    try {
        val networkManager =
            (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val builder = NetworkRequest.Builder()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            networkManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    isConnected = false
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isConnected = true
                }
            })
            return isConnected
        } else {
            return networkManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    } catch (e: Exception) {
        isConnected = false
        return false
    }
}

