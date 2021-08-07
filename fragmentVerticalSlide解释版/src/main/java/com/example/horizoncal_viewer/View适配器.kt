package com.example.horizoncal_viewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class View适配器(fm: FragmentManager, list :List<Fragment>) : FragmentStatePagerAdapter(fm) {
    private lateinit var fragment列表 :List<Fragment>

    init {
        fragment列表=list
    }
    override fun getCount(): Int {
        return fragment列表.size
    }

    override fun getItem(偏短序号: Int): Fragment {
        return fragment列表.get(偏短序号)
    }

}