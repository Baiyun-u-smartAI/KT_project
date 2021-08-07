package com.example.horizoncal_viewer.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.horizoncal_viewer.R

class 第一页片段 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val 主视图:ViewGroup=inflater.inflate(R.layout.page_1,container,false) as ViewGroup
        // 获取片段内元素方法
        var text第一页:TextView=主视图.findViewById(R.id.第一页)
        text第一页.setOnClickListener{
            text第一页.textSize=text第一页.textSize/3.5f-1
        }

        return 主视图
    }


}