package jp.zyyx.favme

import android.app.Application
import jp.zyyx.favme.connectmanager.NetworkConnectivityChecker
import jp.zyyx.favme.data.local.MySharePreference

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MySharePreference.init(this)
        NetworkConnectivityChecker.init(this)
    }
}