package com.lk.sf.smartfactoryworker.http.response

import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.http.BaseResponse

/**
 * @author: winton
 * @time: 2018/9/4 下午5:30
 * @desc: 描述
 */
class BindDevicesResponse:BaseResponse() {

    var data:DataBean? = null

    class DataBean{

        var list:List<ListBean>? = null

        class ListBean{
            var device:Device? = null
        }
    }

}