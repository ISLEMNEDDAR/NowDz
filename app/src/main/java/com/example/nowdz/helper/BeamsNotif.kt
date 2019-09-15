package com.example.nowdz.helper

import com.pusher.pushnotifications.auth.AuthData
import com.pusher.pushnotifications.auth.AuthDataGetter
import com.pusher.pushnotifications.auth.BeamsTokenProvider
import java.math.BigInteger

class BeamsNotif {

    companion object{
        /**
         *
         */
        fun tokenProvider(userId : String): BeamsTokenProvider {
            return BeamsTokenProvider(
                "http://d568b38f.ngrok.io/api/pusher/beams-auth",
                object : AuthDataGetter{
                    override fun getAuthData(): AuthData {
                        return AuthData(
                            queryParams = hashMapOf(
                                "user_id" to userId
                            )
                        )
                    }
                }
            )
        }

        /**
         *
         */

    }

}