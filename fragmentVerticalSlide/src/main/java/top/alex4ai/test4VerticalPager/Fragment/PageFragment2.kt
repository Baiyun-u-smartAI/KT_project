package top.alex4ai.test4VerticalPager.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.page_1.view.*
import top.alex4ai.test4VerticalPager.R

class PageFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView:ViewGroup=inflater.inflate(R.layout.page_2,container,false) as ViewGroup
        
        return rootView
    }


}