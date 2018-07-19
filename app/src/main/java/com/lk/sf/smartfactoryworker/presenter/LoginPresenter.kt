package com.lk.sf.smartfactoryworker.presenter

import com.blankj.utilcode.util.SPUtils
import com.lk.sf.smartfactoryworker.constant.Constant
import com.lk.sf.smartfactoryworker.contract.LoginContract
import com.lk.sf.smartfactoryworker.http.BaseResponse
import com.lk.sf.smartfactoryworker.http.BaseSubscriber

/**
 * @author: winton
 * @time: 2018/7/8 16:09
 * @package: com.lk.sf.smartfactoryworker.presenter
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 登录页Presenter
 */
class LoginPresenter:BasePresenter,LoginContract.Presenter {

    private val mView:LoginContract.View

    constructor(view:LoginContract.View){
        mView = view
    }

    override fun doLogin(username: String, password: String) {
        mView.onLoginSuccess()
        with(SPUtils.getInstance()){
            put(Constant.SP_USERNAME,username)
            put(Constant.SP_PASSWORD,password)
        }
        /**
         * 登录成功后，登录失效状态取消
         */
        BaseSubscriber.isCodeTimeOut = false
    }

    override fun doInit() {
        var username = SPUtils.getInstance().getString(Constant.SP_USERNAME)
        var password = SPUtils.getInstance().getString(Constant.SP_PASSWORD)
        username?.let { mView.fitUsername(it) }
        password?.let{mView.fitPassword(it)}
    }

}