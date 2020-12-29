package com.example.asyncawait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CoroutineScope(IO).launch { //DE ESTA FORMA SE HACE SECUENCIALMENTE
//            Log.i("MyTag","Calculation started...")
//            val stock1 = getStock1()
//            val stock2 = getStock2()
//            val total = stock1 + stock2
//            Log.i("MyTag","total es ${total}")
//        }

//        CoroutineScope(IO).launch { //DE ESTA FORMA SE HACE asincocronicamente
//            Log.i("MyTag","Calculation started...")
//            val stock1 = async {getStock1()}
//            val stock2 = async {getStock2()}
//            val total = stock1.await() + stock2.await()
//            Log.i("MyTag","total es ${total}")
//        }

        CoroutineScope(Main).launch { //DE ESTA FORMA SE HACE asincocronicamente(otra forma)
            Log.i("MyTag","Calculation started...")
            val stock1 = async(IO) {getStock1()}
            val stock2 = async(IO) {getStock2()}
            val total = stock1.await() + stock2.await()
            Toast.makeText(this@MainActivity,"total es ${total}",Toast.LENGTH_LONG).show()
//            Log.i("MyTag","total es ${total}")
        }
    }

    private suspend fun getStock1() : Int{
        delay(10000) //tiempo que toma la logica en hacer/consultar datos
        Log.i("MyTag","stock1 returned")
        return 55000
    }

    private suspend fun getStock2() : Int{
        delay(8000) //tiempo que toma la logica en hacer/consultar datos
        Log.i("MyTag","stock2 returned")
        return 35000
    }

//    private suspend fun getStock3() : Int{
//        delay(1000) //tiempo que toma la logica en hacer/consultar datos
//        Log.i("MyTag","stock1 returned")
//        return 55000
//    }
//
//    private suspend fun getStock4() : Int{
//        delay(1000) //tiempo que toma la logica en hacer/consultar datos
//        Log.i("MyTag","stock1 returned")
//        return 55000
//    }
}