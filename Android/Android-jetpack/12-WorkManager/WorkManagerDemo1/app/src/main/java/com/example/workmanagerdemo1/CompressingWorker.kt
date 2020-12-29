package com.example.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CompressingWorker(context:Context, params:WorkerParameters) : Worker(context,params){
    //Cuando las condiciones especificas en esta clase se cumplan se ejecutará la clase...
    //    OneTimeWorkRequest
    override fun doWork(): Result {
        try {
            for (i in 0..300) {
                Log.i("MYTAG", "Compressing $i")
            }

            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }
    }


}