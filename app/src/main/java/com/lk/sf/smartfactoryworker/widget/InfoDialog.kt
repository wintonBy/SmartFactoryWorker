package com.lk.sf.smartfactoryworker.widget

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.lk.sf.smartfactoryworker.R


/**
 * @author: winton
 * @time: 2018/7/13 15:45
 * @package: com.lk.sf.smartfactoryworker.widget
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 展示信息的基本dialog
 */
open class InfoDialog {

    private lateinit var mContext: Context
    private lateinit var mDialogTip: AlertDialog
    private lateinit var mTextTitle: TextView
    private lateinit var mBtnConfirm: Button
    private lateinit var mBtnCancel: Button
    private var isShow = false

    constructor(context: Context,title:String,confirmRes:Int,cancelRes:Int?){
        mContext = context
        mDialogTip = AlertDialog.Builder(context).create()
        mDialogTip.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialogTip.setCancelable(false)
        mDialogTip.show()

        var view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_info,null)
        mTextTitle = view.findViewById(R.id.dialog_title)
        mBtnConfirm = view.findViewById(R.id.btn_confirm)
        mBtnCancel = view.findViewById(R.id.btn_cancel)
        mTextTitle.text = title
        mBtnConfirm.setText(confirmRes)
        if(cancelRes == null){
           mBtnCancel.run { visibility = View.GONE }
        }else{
            mBtnCancel.setText(cancelRes)
        }
        mBtnConfirm.setOnClickListener {
            hide()
            confirm()
        }
        mBtnCancel.setOnClickListener {
            hide()
            cancel()
        }
        with(mDialogTip.window){
            setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(view)
            setLayout(ConvertUtils.dp2px(270f), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        isShow = true
    }

    private fun hide(){
        isShow = false
        mDialogTip.dismiss()
    }
    fun show(){
        isShow = true
        mDialogTip.show()
    }

    open fun confirm(){

    }
    open fun cancel(){

    }




}