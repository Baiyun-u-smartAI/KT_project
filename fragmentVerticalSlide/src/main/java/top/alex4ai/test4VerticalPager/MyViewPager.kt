package top.alex4ai.test4VerticalPager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyViewPager : ViewPager {


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    private fun init() {
        setPageTransformer(true, VerticalPage())
        overScrollMode = View.OVER_SCROLL_NEVER
    }


    private fun getIntercambioXY(ev: MotionEvent): MotionEvent {
        val width:Float = width.toFloat()
        val height:Float = height.toFloat()
        val newX:Float = ev.y / height * width
        val newY:Float = ev.x / width * height
        ev.setLocation(newX, newY)
        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(getIntercambioXY(ev))
        getIntercambioXY(ev)
        return intercepted
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(getIntercambioXY(ev))
    }


    private inner class VerticalPage : ViewPager.PageTransformer {

        override fun transformPage(view: View, postion: Float) {
            if (postion < -1) {
                view.alpha = 0f

            } else if (postion <= 1) {
                view.alpha = 1f
                view.translationX = view.width * -postion
                val yp:Float = postion * view.height
                view.translationY = yp

            } else {
                view.alpha = 0f

            }
        }
    }
}
