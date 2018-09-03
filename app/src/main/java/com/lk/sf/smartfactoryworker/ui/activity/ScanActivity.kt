package com.lk.sf.smartfactoryworker.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActScanBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import com.zxing.activity.CaptureFragment
import com.zxing.activity.CodeUtils

/**
 * @author: winton
 * @time: 2018/7/24 14:26
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 扫码页面
 */
class ScanActivity :BaseActivity<ActScanBinding,BasePresenter>(){

    private val fragment:CaptureFragment by lazy {
        CaptureFragment()
    }

    private var isLightOpen = false

    private val analyzeCallback:CodeUtils.AnalyzeCallback by lazy {
        object :CodeUtils.AnalyzeCallback{
            override fun onAnalyzeSuccess(mBitmap: Bitmap?, result: String?) {
                var intent=Intent()
                var bundle = Bundle()
                bundle.putInt(CodeUtils.RESULT_TYPE,CodeUtils.RESULT_SUCCESS)
                bundle.putString(CodeUtils.RESULT_STRING,result)
                intent.putExtras(bundle)
                this@ScanActivity.setResult(android.app.Activity.RESULT_OK,intent)
                this@ScanActivity.finish()
            }

            override fun onAnalyzeFailed() {
                var intent=Intent()
                var bundle = Bundle()
                bundle.putInt(CodeUtils.RESULT_TYPE,CodeUtils.RESULT_FAILED)
                bundle.putString(CodeUtils.RESULT_STRING,"")
                intent.putExtras(bundle)
                this@ScanActivity.setResult(android.app.Activity.RESULT_OK,intent)
                this@ScanActivity.finish()
            }
        }
    }
    companion object {
        fun startForResult(context: Activity,title:String,reqCode:Int ){
            var intent = Intent(context,ScanActivity::class.java)
            intent.putExtra("title",title)
            context.startActivityForResult(intent,reqCode)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.act_scan
    }

    override fun initListener() {
        super.initListener()
        binding!!.action!!.findViewById<View>(R.id.iv_back).setOnClickListener { finish() }
        binding!!.btLightSwitch.setOnClickListener {
            CodeUtils.isLightEnable(!isLightOpen)
            isLightOpen = !isLightOpen
            if(isLightOpen){
                it.setBackgroundResource(R.mipmap.ic_light_open)
            }else{
                it.setBackgroundResource(R.mipmap.ic_light_off)
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        binding!!.action!!.findViewById<TextView>(R.id.tv_title).text = intent.getStringExtra("title")
        CodeUtils.setFragmentArgs(fragment,R.layout.layout_scan)
        fragment.analyzeCallback = analyzeCallback
        supportFragmentManager.beginTransaction().replace(R.id.fl_my_container,fragment).commit()
    }
}