package com.lk.sf.smartfactoryworker.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.PI

/**
 * @author: winton
 * @time: 2018/8/2 10:13
 * @package: com.lk.sf.smartfactoryworker.widget
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 加载中。。。View
 */
class LoadingView: View {

    private lateinit var mPaint:Paint
    private var circleNumber = 4
    private var colors = intArrayOf(Color.BLUE,Color.YELLOW,Color.RED,Color.GREEN)
    private var mViewSize:Int = 0
    private var mCircleR :Float= 0f
    private var viewCx = 0
    private var viewCy = 0
    private var angle:Float = 0f
    private var flag:Float = 0f
    private var angleSpace = 0.0
    private var dir = 0

    private var mAngleAnim:ValueAnimator? = null
    private var mFlagAnim:ValueAnimator? = null
    private var mDirectAnim:ValueAnimator? = null

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        initData()
    }
    private fun initData(){
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.FILL
        angleSpace = (2 * PI / circleNumber)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for(i in 0 until circleNumber){
            var cx = computerX(i)
            var cy = computerY(i)
            mPaint.color = colors[i]
            drawCircle(canvas!!,cx,cy)
        }
    }

    private fun computerY(i: Int): Float {
        var realAngle = computerAngle(i)
        return (viewCy + (mViewSize/2 - mCircleR) * Math.sin(realAngle)).toFloat()
    }

    private fun computerAngle(i: Int): Double {
        if(dir == 0){
            return  angle - angleSpace * flag * i
        }else{
            return  angle - angleSpace * flag * (circleNumber - i)
        }


    }

    private fun computerX(i: Int): Float {
        var realAngle = computerAngle(i)
        return (viewCx + (mViewSize/2 - mCircleR) * Math.cos(realAngle)).toFloat()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewSize = Math.min(measuredWidth-paddingLeft-paddingRight,measuredHeight-paddingBottom-paddingTop)
        mCircleR = mViewSize/8f
        viewCx = paddingLeft + mViewSize/2
        viewCy = paddingTop + mViewSize/2
    }

    /**
     * 绘制一个小圆
     */
    private fun drawCircle(canvas: Canvas,x: Float,y:Float){
        canvas.drawCircle(x,y,mCircleR,mPaint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mAngleAnim = ValueAnimator.ofFloat(0f, (PI*2).toFloat())
        mAngleAnim!!.duration = 2000
        mAngleAnim!!.interpolator = LinearInterpolator()
        mAngleAnim!!.repeatCount = ValueAnimator.INFINITE
        mAngleAnim!!.addUpdateListener {
            angle = (it.animatedValue)as Float
            invalidate()
        }
        mFlagAnim = ValueAnimator.ofFloat(0.1f,1f)
        mFlagAnim!!.duration = 1000
        mFlagAnim!!.interpolator = LinearInterpolator()
        mFlagAnim!!.repeatCount = ValueAnimator.INFINITE
        mFlagAnim!!.repeatMode = ValueAnimator.REVERSE
        mFlagAnim!!.addUpdateListener {
            flag = it.animatedValue as Float
        }

        mAngleAnim!!.start()
        mFlagAnim!!.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAngleAnim?.end()
    }

}