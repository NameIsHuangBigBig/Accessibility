package primary.student.accessibility.accessibilityService

import android.accessibilityservice.AccessibilityButtonController
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi

/**
 * @author HuangJiaHeng
 * @date 2020/5/6.
 */
class WXAccessService: AccessibilityService()  {

    val TAG = "WXAccessService"

    private val nameArray = arrayOf("黄嘉衡","李光洪","林海氽","劳汉文","方嘉耀","吴彤林","杨泽迦")

    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        val type = event.eventType
        val classNameChr = event.className
        val className = classNameChr?.toString()

        when(type){
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ->{
                Log.e(TAG, "屏幕变化：当前类名$className")
                if (className == "com.tencent.mm.ui.LauncherUI"){
                   checkTXL()
                }else{
                    openWX()
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    private fun openWX(){

        var node = rootInActiveWindow
        if (node != null){
            var list = node.findAccessibilityNodeInfosByText("微信")

            for(i in list){
                if (i.text == "微信"){
                    i.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
            }
        }
    }

    private fun checkTXL(){

        var node = rootInActiveWindow
        if (node != null){

            val list = node.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cl7")

            for(i in list){
                if (i.text == "通讯录"){
                    Log.e(TAG,"点击通讯录")
                    i.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    openGroup()
                }
            }
        }
    }

    private fun openGroup(){
        var node = rootInActiveWindow
        if (node != null){

            var list = node.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/fx")

            for(i in list){
                if (i.text == "群聊"){
                    Log.e(TAG,"点击群聊")
                    i.parent.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    openHLW()
                }
            }
        }

    }

    private fun openHLW(){
        var node = rootInActiveWindow
        if (node != null){

            var list = node.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b10")

            for(i in list){
                Log.e(TAG,"群聊信息：${i.text}")
                if (i.text.contains("葫芦娃")){
                    Log.e(TAG,"打开葫芦娃")
                    i.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    fillText()
                    break
                }
            }
        }
    }

    private fun fillText(){

        var node = rootInActiveWindow
        if (node != null){

            var list = node.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ak7")
            for (i in nameArray){
                for (i in list){
                    val arguments = Bundle()
                    arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"${nameArray[(Math.random()*7).toInt()]}屌炸了")
                    i.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT,arguments)
                }

//                var btnList = node.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/amr")
//                btnList[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)

            }
        }

    }

}