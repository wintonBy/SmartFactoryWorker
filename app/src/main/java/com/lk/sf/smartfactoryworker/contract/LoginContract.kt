package com.lk.sf.smartfactoryworker.contract

import com.lk.sf.smartfactoryworker.presenter.BasePresenter

/**
 * @author: winton
 * @time: 2018/7/8 16:07
 * @package: com.lk.sf.smartfactoryworker.contract
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 登录页协议
 */
interface LoginContract {

    interface View{
        fun showError(msg:String)

        fun toWorkIndex()

        fun toSupportIndex()

        fun showLoading(show:Boolean)

        fun fitUsername(username: String)

        fun fitPassword(password: String)
    }

    interface Presenter:BasePresenter{
        /**
         * 进行登录
         */
        fun doLogin(username:String,password:String)

        /**
         * 初始化
         */
        fun doInit()

    }
}