package com.tar.investnotes

class Constants {

    interface Default {
        companion object {
            const val API_URL = "http://188.225.11.47/"
            const val API_ME_INDEX_URL = "https://iss.moex.com/iss/securities.xml?q="
            const val API_ME_INDEX_PARAMS = "&iss.meta=off&securities.columns=shortname,name,secid,primary_boardid,emitent_title"
//            const val API_YAHOO_FINANCE = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q="
//            const val API_YAHOO_FINANCE_PARAMS = "&callback=YAHOO.Finance.SymbolSuggest.ssCallback&region=US&lang=en-US"
            const val API_YAHOO_FINANCE = "http://autoc.finance.yahoo.com/autoc?query="
            const val API_YAHOO_FINANCE_PARAMS = "&region=US"
            const val STOCK_API_KEY = "bu9ikvf48v6tjsddpdi0"
            const val DEBUG = true

//             http://autoc.finance.yahoo.com/autoc?query=yndx&callback=YAHOO.Finance.SymbolSuggest.ssCallback&region=US&lang=en-US

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

    interface Sort {
        companion object {
            const val ALL = "all"
            const val OWNER = "owner"
            const val BROKER = "broker"
            const val TYPE = "type"
        }
    }
}