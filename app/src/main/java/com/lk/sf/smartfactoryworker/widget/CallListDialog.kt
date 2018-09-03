package com.lk.sf.smartfactoryworker.widget

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.blankj.utilcode.util.ToastUtils
import com.lk.sf.smartfactoryworker.BR
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.adapter.BaseRVAdapter
import com.lk.sf.smartfactoryworker.bean.Caller
import com.lk.sf.smartfactoryworker.databinding.LayoutDialogCallListBinding
import com.lk.sf.smartfactoryworker.model.CallerListViewModel
import com.lk.sf.smartfactoryworker.repository.Resource
import com.lk.sf.smartfactoryworker.utils.BindingUtils
import com.lk.sf.smartfactoryworker.utils.diffutils.CallerDiffCallback
import kotlinx.android.synthetic.main.layout_dialog_call_list.*

/**
 * @author: winton
 * @time: 2018/7/30 22:45
 * @package: com.lk.sf.smartfactoryworker.widget
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 呼叫人列表
 */
class CallListDialog:DialogFragment() {

    private lateinit var model:CallerListViewModel
    var role:String? = null

    companion object {
        fun newInstance():CallListDialog{
            var dialog = CallListDialog();
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var style = DialogFragment.STYLE_NO_TITLE
        var theme = android.R.style.Theme_Holo_Light_Dialog
        setStyle(style,theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = DataBindingUtil.inflate<LayoutDialogCallListBinding>(inflater, R.layout.layout_dialog_call_list,container,false)
        initData(binding)
        return binding.root
    }

    private fun initData(binding: LayoutDialogCallListBinding){
        binding.rv.layoutManager = LinearLayoutManager(context)
        var adapter = BaseRVAdapter(CallerDiffCallback(),BR.caller,R.layout.layout_item_caller)
        binding.rv.adapter = adapter
        model = ViewModelProviders.of(this).get(CallerListViewModel::class.java)
        model.getCallers().observe(this, Observer {
           when(it!!.status){
               Resource.SUCCESS -> adapter.submitList(it.data)
               Resource.ERROR -> {
                   adapter.submitList(ArrayList<Caller>())
               }
           }
        })
    }

    override fun onStart() {
        super.onStart()
        var window = dialog.window
        var params = window.attributes
        params.gravity = Gravity.CENTER
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = params
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        role?.let {
            model.load(role!!)
        }
    }


}