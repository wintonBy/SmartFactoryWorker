package com.lk.sf.smartfactoryworker.ui.activity

import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActSplashBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import java.net.HttpURLConnection

/**
 * @author: winton
 * @time: 2018/7/6 16:00
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 启动页
 */
class SplashActivity:BaseActivity<ActSplashBinding,BasePresenter>(){


    override fun getLayoutId(): Int {
        return R.layout.act_splash

    }

    override fun onResume() {
        super.onResume()
        toNextView()
    }

    private fun toNextView(){
        window.decorView.postDelayed(Runnable {
            LoginActivity.start(this@SplashActivity)
            this@SplashActivity.finish()
        },2000);
    }

}