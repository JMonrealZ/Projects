package com.anushka.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var smartPhone: SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region Ejemplo viejo
//        val smartPhone = SmartPhone(
//            Battery(),
//            SIMCard(ServiceProvider()),
//            MemoryCard()
//        )
//            .makeACallWithRecording()
        //endregion

        //region Ejemplo1 con esto construimos los objetos fuera de la clase y los inyectamos...
        DaggerSmartPhoneComponent.create()
            .getSmartPhone()
            .makeACallWithRecording()
        //endregion

        //region Ejemplo2 con dependencias de terceros("Memory card")

    }
}
