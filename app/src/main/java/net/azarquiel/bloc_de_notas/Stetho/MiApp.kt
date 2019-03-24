package net.azarquiel.bloc_de_notas.Stetho

import android.app.Application
import com.facebook.stetho.Stetho

class MiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Para stetho de Realm
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }
}
