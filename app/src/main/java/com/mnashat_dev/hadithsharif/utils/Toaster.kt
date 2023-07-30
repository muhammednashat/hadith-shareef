package com.mnashat_dev.hadithsharif.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.mnashat_dev.hadithsharif.R

class Toaster(var context: Context) : Toast(context) {
    fun makeToast(message: String?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.toast_custom_layout, null)
        val tv_toast = view.findViewById<View>(R.id.msg) as TextView
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        val screenWidth = metrics.widthPixels
        tv_toast.width = screenWidth
        val toast = Toast(context)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)
        tv_toast.text = message
        toast.setView(view)
        toast.duration = LENGTH_LONG
        toast.show()
    }
}