package com.lk.sf.smartfactoryworker.http.response

import com.lk.sf.smartfactoryworker.bean.User
import com.lk.sf.smartfactoryworker.http.BaseResponse

/**
 * @author: winton
 * @time: 2018/8/24 17:17
 * @package: com.lk.sf.smartfactoryworker.http.response
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 登录响应
 */
class LoginResponse:BaseResponse() {

    var data:DataBean? = null

    class DataBean{
        var employee:User? = null
    }
}

