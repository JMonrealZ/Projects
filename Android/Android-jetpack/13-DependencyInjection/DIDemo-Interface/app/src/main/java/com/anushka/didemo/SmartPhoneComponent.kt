package com.anushka.didemo

import dagger.Component

@Component(modules = [MemoryCardModule::class,NCBatteryModule::class])
interface SmartPhoneComponent {
    //funcion para obtener la dependencia
    fun getSmartPhone() : SmartPhone

    //
}