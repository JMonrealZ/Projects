package com.anushka.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var smartPhone: SmartPhone

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

        //region Ejemplo3 con esto construimos los objetos fuera de la clase y los inyectamos...
        DaggerSmartPhoneComponent.create()
            .inject(this)
        smartPhone.makeACallWithRecording()
        //endregion

    }
}
