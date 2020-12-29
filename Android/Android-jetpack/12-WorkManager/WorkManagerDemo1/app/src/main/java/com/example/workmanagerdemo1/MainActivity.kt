package com.example.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            //setOneTimeWorkRequest()
            setPeriodicWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest(){
        //regionEjemplo 1
//        val otwr = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .build()
//
//        //getInstance ejecutará la tarea
//        WorkManager.getInstance(applicationContext)
//            .enqueue(otwr)
        //endregion

        //region Ejemplo 2 usando workInfo(retorno de la función)
//        val workManager = WorkManager.getInstance(applicationContext)
//        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .build()
//        workManager.enqueue(uploadRequest)
//
//        //observamos resultado
//        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
//            .observe(this, Observer {
//                textView.text = it.state.name
//            })
        //endregion

        //region Ejemplo3 workInfo con constraints
//        val workManager = WorkManager.getInstance(applicationContext)
//
//        //Limitamos a ciertas condiciones el work
//        val constraints = Constraints.Builder()
//            .setRequiresCharging(true)
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .setConstraints(constraints)
//            .build()
//        workManager.enqueue(uploadRequest)
//
//        //observamos resultado
//        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
//            .observe(this, Observer {
//                textView.text = it.state.name
//            })
        //endregion

        //region Ejemplo4 constraint, input and output data
//        val workManager = WorkManager.getInstance(applicationContext)
//
//        //pasamos data al worker
//        val data:Data = Data.Builder()
//            .putInt(KEY_COUNT_VALUE,125)
//            .build()
//
//        //Limitamos a ciertas condiciones el work
//        val constraints = Constraints.Builder()
//            .setRequiresCharging(true)
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .setConstraints(constraints)
//            .setInputData(data)
//            .build()
//        workManager.enqueue(uploadRequest)
//
//        //observamos resultado
//        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
//            .observe(this, Observer {
//                textView.text = it.state.name
//
//                if(it.state.isFinished){
//                    val data = it.outputData
//                    val message = data.getString(UploadWorker.KEY_WORKER)
//                    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
//                }
//
//            })
        //endregion

        //region Ejemplo5 encadenamiento
//        val workManager = WorkManager.getInstance(applicationContext)
//
//        //pasamos data al worker
//        val data:Data = Data.Builder()
//            .putInt(KEY_COUNT_VALUE,125)
//            .build()
//
//        //Limitamos a ciertas condiciones el work
//        val constraints = Constraints.Builder()
//            .setRequiresCharging(true)
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
//            .build()
//
//        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
//            .build()
//
//        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .setConstraints(constraints)
//            .setInputData(data)
//            .build()
//
//        //encadenamiento
//        workManager
//            .beginWith(filteringRequest)
//            .then(compressingRequest)
//            .then(uploadRequest)
//            .enqueue()
//
//        //observamos resultado
//        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
//            .observe(this, Observer {
//                textView.text = it.state.name
//
//                if(it.state.isFinished){
//                    val data = it.outputData
//                    val message = data.getString(UploadWorker.KEY_WORKER)
//                    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
//                }
//
//            })
        //endregion

        //region Ejemplo6 parallel
        val workManager = WorkManager.getInstance(applicationContext)

        //pasamos data al worker
        val data:Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()

        //Limitamos a ciertas condiciones el work
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        //Lista para el paralelismo
        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingRequest)
        parallelWorks.add(filteringRequest)

        workManager
            .beginWith(parallelWorks)
            .then(compressingRequest)
            .then(uploadRequest)
            .enqueue()

        //observamos resultado
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name

                if(it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
                }

            })
        //endregion
    }

    private fun setPeriodicWorkRequest(){
        //repeat interval y tiempo
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(DownloadingWorker::class.java,16, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}