package com.example.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.tab.fragment.Fragment1
import com.example.tab.fragment.Fragment2
import com.example.tab.fragment.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SetupTabs()
    }

    private fun SetupTabs() {
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(Fragment1(),"Page1")
        adapter.addFragment(Fragment2(),"Page2")
        val viewPager=findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_filter_1_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_filter_2_24)

    }
}