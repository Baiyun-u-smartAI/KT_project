package top.alex4ai.test4VerticalPager.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.page_1.view.*
import top.alex4ai.test4VerticalPager.R

class PageFragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView:ViewGroup=inflater.inflate(R.layout.page_1,container,false) as ViewGroup
        var textView:TextView=rootView.findViewById(R.id.第一页)
        textView.setOnClickListener{
            textView.textSize=textView.textSize/3.5f-1
        }

        return rootView
    }


}