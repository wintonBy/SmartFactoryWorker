package com.lk.sf.smartfactoryworker.widget

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.lk.sf.smartfactoryworker.R
import io.reactivex.Observable

/**
 * @author: winton
 * @time: 2018/7/26 23:19
 * @package: com.lk.sf.smartfactoryworker.widget
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 权限说明对话框
 */
class PermissionInfoDialog:DialogFragment() {
    private lateinit var btConfirm:Button
    private lateinit var tvInfo:TextView
    private var info:String = ""

    private var listener:() -> Unit = {}

    companion object {
        fun newInstance():PermissionInfoDialog{
            return PermissionInfoDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var style = DialogFragment.STYLE_NO_TITLE
        var theme = android.R.style.Theme_Holo_Light_Dialog
        setStyle(style,theme)
    }

    override fun onStart() {
        super.onStart()
        var window = dialog.window
        var wParams = window.attributes
        wParams.gravity = Gravity.CENTER
        wParams.width = WindowManager.LayoutParams.MATCH_PARENT
        wParams.height = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = wParams
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.layout_dialog_permission_info,null)
        btConfirm = rootView.findViewById<Button>(R.id.bt_confirm)!!
        tvInfo = rootView.findViewById<TextView>(R.id.tv_info)!!
        tvInfo.text = info
        initListener()
        return rootView
    }

    private fun initListener(){
        btConfirm.setOnClickListener {
            listener()
            this@PermissionInfoDialog.dismiss()
        }
    }

    public fun setInfo(text:String){
       info = text
    }

    public fun onConfirm(listener:()->Unit){
        this.listener = listener
    }

}