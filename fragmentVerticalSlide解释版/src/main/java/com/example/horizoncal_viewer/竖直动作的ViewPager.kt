package com.example.horizoncal_viewer
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class 竖直动作的ViewPager : ViewPager {


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    private fun init() {
        setPageTransformer(true, 显示滑动特效())
        overScrollMode = View.OVER_SCROLL_NEVER

    }


    private fun 横竖动作翻转函数(ev: MotionEvent): MotionEvent {
        val width:Float = width.toFloat()
        val height:Float = height.toFloat()

        // 将y方向的动作转换成x方向
        val newX:Float = ev.y / height * width
        val newY:Float = ev.x / width * height
        ev.setLocation(newX, newY)

        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(横竖动作翻转函数(ev))
        横竖动作翻转函数(ev)

        return intercepted
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {

        return super.onTouchEvent(横竖动作翻转函数(ev))
    }


    private inner class 显示滑动特效 : ViewPager.PageTransformer {

        override fun transformPage(view: View, position: Float) {

            if (position < -1) {
                view.alpha = 0f

            } else if (position <= 1) { // (0,1]// Fade the page out.
                view.setAlpha(1 - position);

                // 为了抵消默认左右滑动效果
                view.translationX = view.width * -position

                // 添加上下滑动效果
                val yp:Float = position * view.height
                view.translationY = yp

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha=0f

            }
        }
    }
}