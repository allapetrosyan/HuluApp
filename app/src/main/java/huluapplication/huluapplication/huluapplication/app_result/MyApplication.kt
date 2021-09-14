package huluapplication.huluapplication.huluapplication.app_result

import android.app.Application
import huluapplication.huluapplication.huluapplication.module.DmModule.Companion.apiModule
import huluapplication.huluapplication.huluapplication.module.DmModule.Companion.networkModule
import huluapplication.huluapplication.huluapplication.module.DmModule.Companion.repositoryModule
import huluapplication.huluapplication.huluapplication.module.DmModule.Companion.viewModelModule
import huluapplication.huluapplication.huluapplication.module.DmModule.Companion.viewModelModule2
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                viewModelModule2
            )
        }
    }
}