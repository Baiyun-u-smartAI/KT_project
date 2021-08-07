package com.example.horizoncal_viewer.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horizoncal_viewer.R

class 第二页片段 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val 主视图:ViewGroup=inflater.inflate(R.layout.page_2,container,false) as ViewGroup



        return 主视图
    }


}