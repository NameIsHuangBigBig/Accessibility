package primary.student.accessibility.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS
import android.provider.Settings.ACTION_WIFI_SETTINGS
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import primary.student.accessibility.util.ToastUtils
import primary.student.accessibility.workManager.BackgroundService


/**
 * @author HuangJiaHeng
 * @date 2020/5/6.
 */
class MainActivityVM (private val mActivity:MainActivity){
    var mTvCommand = "打开微信，打开葫芦娃群，输入：***屌爆了"

    fun sendCommand(btn: View){
        if (!checkAccessibility()){
            goSettingActivity()
            return
        }
        val intent = Intent(mActivity, BackgroundService::class.java)
        BackgroundService.isLife = false
        mActivity.showLoading()
        Handler().postDelayed({
            mActivity.dismissLoading()
            goBackHome()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mActivity.startForegroundService(intent)
            } else {
                mActivity.startService(intent)
            }

        },3000)


    }


    private fun checkAccessibility():Boolean{
        var accessibilityManager = mActivity.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        return  accessibilityManager.isEnabled
    }


    private fun goBackHome(){
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN // "android.intent.action.MAIN"
        intent.addCategory(Intent.CATEGORY_HOME) //"android.intent.category.HOME"
        mActivity.startActivity(intent)
    }

    private fun goSettingActivity(){
        val intent = Intent(ACTION_ACCESSIBILITY_SETTINGS)
        mActivity.startActivity(intent)
        ToastUtils.get.showText("请打开Accessibility无障碍服务")
    }
}