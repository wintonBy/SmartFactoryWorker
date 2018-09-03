package com.lk.sf.smartfactoryworker.presenter

import com.blankj.utilcode.util.SPUtils
import com.lk.sf.smartfactoryworker.bean.User
import com.lk.sf.smartfactoryworker.constant.Constant
import com.lk.sf.smartfactoryworker.contract.LoginContract
import com.lk.sf.smartfactoryworker.http.BaseResponse
import com.lk.sf.smartfactoryworker.http.BaseSubscriber
import com.lk.sf.smartfactoryworker.http.RetrofitClient
import com.lk.sf.smartfactoryworker.http.response.LoginResponse
import org.reactivestreams.Subscription

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
    private val mSubscriber = ArrayList<Subscription>()

    constructor(view:LoginContract.View){
        mView = view
    }

    override fun doLogin(username: String, password: String) {

        RetrofitClient.doLogin(username,password, object:BaseSubscriber<LoginResponse>(){
            override fun onStart(s: Subscription) {
                super.onStart(s)
                mSubscriber.add(s)
                mView.showLoading(true)
            }

            override fun onSuccess(response: LoginResponse) {
                mView.showLoading(false)
                super.onSuccess(response)
                response.data?.run {
                    save(this.employee!!)
                }?:kotlin.run {
                    mView.showError("数据异常")
                }
                BaseSubscriber.isCodeTimeOut = false
                with(SPUtils.getInstance()){
                    put(Constant.SP_USERNAME,username)
                    put(Constant.SP_PASSWORD,password)
                }
            }

            override fun onFailed(code: Int?, msg: String?) {
                mView.showLoading(false)
                super.onFailed(code, msg)
                mView.showError(msg?:"登录失败")
            }
        })
    }

    /**
     * 保存用户信息
     */
    private fun save(user: User){
        with(SPUtils.getInstance()){
            put(Constant.SP_USER_GENDER,user.gender)
            put(Constant.SP_USER_NAME,user.name)
            put(Constant.SP_USER_TYPE,user.type)
            put(Constant.SP_USER_PHONE,user.phone)
            put(Constant.SP_USER_NO,user.no)
        }
    }

    override fun doInit() {
        var username = SPUtils.getInstance().getString(Constant.SP_USERNAME)
        var password = SPUtils.getInstance().getString(Constant.SP_PASSWORD)
        username?.let { mView.fitUsername(it) }
        password?.let{mView.fitPassword(it)}
    }

    override fun onDestroy() {
        for(sub in mSubscriber){
            sub.cancel()
        }
    }
}