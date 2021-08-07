package top.alex4ai.test4VerticalPager

import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewAdapter(fm: FragmentManager,list :List<Fragment>) : FragmentStatePagerAdapter(fm) {
    private lateinit var fragmentLists :List<Fragment>

    init {
        fragmentLists=list
    }
    override fun getCount(): Int {
        return fragmentLists.size
    }

    override fun getItem(p0: Int): Fragment {
        return fragmentLists.get(p0)
    }

}