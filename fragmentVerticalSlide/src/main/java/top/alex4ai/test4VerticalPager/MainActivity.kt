package top.alex4ai.test4VerticalPager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.widget.TextView
import top.alex4ai.test4VerticalPager.Fragment.PageFragment1
import top.alex4ai.test4VerticalPager.Fragment.PageFragment2

class MainActivity : AppCompatActivity() {

    private lateinit var myViewPager: MyViewPager
    private lateinit var viewAdapter: ViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: ArrayList<Fragment> = ArrayList<Fragment>()
        list.add(PageFragment1())
        list.add(PageFragment2())

        myViewPager = findViewById(R.id.翻页器)
        viewAdapter = ViewAdapter(supportFragmentManager,list)
        myViewPager.adapter=viewAdapter

    }
}
