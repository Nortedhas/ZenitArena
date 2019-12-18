package com.ageone.zenit.Network.Socket

import com.ageone.zenit.Application.utils
import com.ageone.zenit.SCAG.DataBase
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import timber.log.Timber

class WebSocket {

    lateinit var socket: Socket

    var isStarted: Boolean = false

    fun initialize() {
        try {
            socket = IO.socket("${DataBase.url}:80")
            socket.connect()

            isStarted = true

            socket
                .on(Socket.EVENT_CONNECT) {
                    val body = JSONObject()
                    body.put("token", utils.variable.token)
                    socket.emit("registration", body)

                    Timber.i("Socket connect")
                }.on(Socket.EVENT_DISCONNECT) {
                    Timber.i("Socket disconnect")
                }

//            subscribeUpdateState()

        } catch (e: Exception) {
            Timber.e("Socket connect error: ${e.message}")
        }
    }

    /*fun subscribeUpdateState() {
        socket.on("updateState") { data ->
            Timber.i("Socket Update State - ${data}")

        }
    }*/

}
