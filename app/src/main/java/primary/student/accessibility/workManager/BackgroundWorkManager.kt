package primary.student.accessibility.workManager

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * @author HuangJiaHeng
 * @date 2020/4/30.
 */
class BackgroundWorkManager (var appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {

    companion object{
        private var  toast:Toast?=null
    }

    override fun doWork(): Result {

        showToast("是否需要重启")

        val intent = Intent(appContext,BackgroundService::class.java)


        return Result.success()
    }

    private fun showToast(message:String){

        Handler(Looper.getMainLooper()).post {
            if (toast==null){
                toast = Toast.makeText(appContext,"",Toast.LENGTH_SHORT)
            }
            toast?.setGravity(Gravity.CENTER,0,0)
            toast?.setText(message)
            toast?.show()
        }

    }

    private fun isServiceWork(serviceName: String): Boolean {
        var isWork = false
        val myAM = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val myList: List<ActivityManager.RunningServiceInfo> = myAM.getRunningServices(40)
        if (myList.isEmpty()) {
            return false
        }
        for (i in myList.indices) {
            val mName: String = myList[i].service.className.toString()
            if (mName == serviceName) {
                isWork = true
                break
            }
        }
        Log.e("TAG","是否允许：$isWork")
        return isWork
    }
}