package com.tar.investnotes

class Constants {

    interface Default {
        companion object {
            const val API_URL = "http://188.225.11.47/"
            const val API_ME_INDEX_URL = "https://iss.moex.com/iss/securities.xml?q="
            const val API_ME_INDEX_PARAMS = "&iss.meta=off&securities.columns=shortname,name,secid,primary_boardid,emitent_title"
            const val STOCK_API_KEY = "bu9ikvf48v6tjsddpdi0"
            const val DEBUG = true
        }
    }

    interface User {
        companion object {
            const val FREE = "free"
            const val FULL = "full"
        }
    }

    interface Settings {
        companion object {
            const val QUIZ_TIME = "quiz_time"
            const val SENT_TIME = "sent_time"
        }
    }
}