package primary.student.accessibility.base.app

import android.app.Application
import primary.student.accessibility.util.ToastUtils

/**
 * @author HuangJiaHeng
 * @date 2020/5/7.
 */
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        ToastUtils.get.init(applicationContext)
    }
}