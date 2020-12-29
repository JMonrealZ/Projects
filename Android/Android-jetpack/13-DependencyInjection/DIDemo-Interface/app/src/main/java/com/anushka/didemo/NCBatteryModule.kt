package com.anushka.didemo

import dagger.Module
import dagger.Provides

@Module
class NCBatteryModule {
    @Provides
    fun providesNCBattery(nickelCadmiumBattery: NickleCadmiumBattery):Battery{
        return nickelCadmiumBattery
    }
    //PODEMOS HACER CÓDIGO MÁS CONCISO USANDO BIND ANNOTATION
}