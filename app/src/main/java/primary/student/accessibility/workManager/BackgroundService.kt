package primary.student.accessibility.workManager

import android.accessibilityservice.AccessibilityButtonController
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import primary.student.accessibility.main.MainActivity
import primary.student.accessibility.R
import kotlin.concurrent.thread


class BackgroundService() : Service() {

    companion object{
        var isLife = true
    }

    private val nameArray = arrayOf("黄嘉衡","李光洪","林海氽","劳汉文","方嘉耀","吴彤林","杨泽迦")

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        isLife = true
        thread {
            while (true){
                Thread.sleep(3000)
                if (!isLife){
                    break
                }
                Log.e("TAG","程序保活！！")
            }

        }



        showService()

        return START_REDELIVER_INTENT
    }


    private fun showService(){
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification: Notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("1", "浪人", NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(mChannel)

            notification = Notification.Builder(this, "1")
                .setContentTitle("祖安狂人")
                .setContentText("出问题请找神仙")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setTicker("你们是来争第二的吗，你们是来争第二的吗")
                .build()


        } else {
            notification = Notification.Builder(this)
                .setContentTitle("祖安狂人")
                .setContentText("出问题请找神仙")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setTicker("你们是来争第二的吗，你们是来争第二的吗")
                .build()
        }

        startForeground(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        isLife = false
        Log.e("TAG","isLife:$isLife")
        Toast.makeText(this,"服务被停止，开启保活程序",Toast.LENGTH_SHORT).show()

    }

    private fun doBackgroundWork(){
        val uploadWorkRequest = OneTimeWorkRequestBuilder<BackgroundWorkManager>()
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
    }
}
