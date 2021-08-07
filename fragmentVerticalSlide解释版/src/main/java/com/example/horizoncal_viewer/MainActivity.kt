package com.example.horizoncal_viewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.horizoncal_viewer.Fragment.第一页片段
import com.example.horizoncal_viewer.Fragment.第二页片段


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: 竖直动作的ViewPager
    private lateinit var view适配器: View适配器


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: ArrayList<Fragment> = ArrayList<Fragment>()
        list.add(第一页片段())
        list.add(第二页片段())

        viewPager = findViewById(R.id.翻页器)
        view适配器 = View适配器(supportFragmentManager,list)
        viewPager.adapter=view适配器

    }
}