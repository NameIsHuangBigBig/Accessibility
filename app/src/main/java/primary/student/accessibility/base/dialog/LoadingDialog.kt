package primary.student.accessibility.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import primary.student.accessibility.R

/**
 * @author HuangJiaHeng
 * @date 2020/5/6.
 */
class LoadingDialog (context: Context):Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}