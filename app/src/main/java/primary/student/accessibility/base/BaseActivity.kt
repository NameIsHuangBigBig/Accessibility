package primary.student.accessibility.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import primary.student.accessibility.base.dialog.LoadingDialog

/**
 * @author HuangJiaHeng
 * @date 2020/5/6.
 */
abstract class BaseActivity<T : ViewDataBinding>:AppCompatActivity(){

    lateinit var  mDataBind :T

    private lateinit var loadingDialog:LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView<T>(this,layoutId())
        loadingDialog = LoadingDialog(this)

        setVM()
        entrance()
    }

    abstract fun layoutId():Int

    abstract fun setVM()

    abstract fun entrance()

    fun showLoading(){
        loadingDialog.show()
    }

    fun dismissLoading(){
        loadingDialog.dismiss()
    }
}