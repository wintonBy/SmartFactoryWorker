package com.lk.sf.smartfactoryworker.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.blankj.utilcode.util.SnackbarUtils
import com.blankj.utilcode.util.ToastUtils
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.contract.LoginContract
import com.lk.sf.smartfactoryworker.databinding.ActLoginBinding
import com.lk.sf.smartfactoryworker.presenter.LoginPresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity

/**
 * @author: winton
 * @time: 2018/7/6 18:19
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 登录页面
 */
class LoginActivity:BaseActivity<ActLoginBinding,LoginPresenter>(),LoginContract.View{
    private var username:String? = null
    private var password:String? = null

    companion object {
        fun start(context: Context){
            var intent = Intent(context,LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fitUsername(username: String) {
        binding!!.etUsername.setText(username)
    }

    override fun fitPassword(password: String) {
        binding!!.etPassword.setText(password)
    }

    override fun getLayoutId(): Int {
        return R.layout.act_login
    }

    override fun initPresenter(): LoginPresenter? {
        return LoginPresenter(this)
    }

    override fun initListener() {
        check()
        with(binding!!){
            btLogin.setOnClickListener {
                mPresenter?.doLogin(username!!,password!!)
            }
            etUsername.setTextChangeListener {
                username = it
                check()
            }
            etPassword.setTextChangeListener {
                password = it
                check()
            }
        }

    }
    private fun check(){
        binding!!.btLogin.isEnabled = (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
    }

    override fun showError(msg: String) {
        SnackbarUtils.with(window.decorView).setMessage(msg).showError()
    }

    override fun onLoginSuccess() {
        WorkerIndexActivity.start(this@LoginActivity)
        this@LoginActivity.finish()
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mPresenter!!.doInit()
    }

    override fun showLoading(show: Boolean) {

    }

    /**
     * 扩展EditText
     */
    private fun  EditText.setTextChangeListener(body:(key:String)->Unit){
        addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                body(s.toString())
            }
        })
    }

}