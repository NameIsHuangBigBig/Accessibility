package primary.student.accessibility.main

import primary.student.accessibility.R
import primary.student.accessibility.base.BaseActivity
import primary.student.accessibility.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val vm = MainActivityVM(this)

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun setVM() {
        mDataBind.vm = vm
    }

    override fun entrance() {

    }

}
