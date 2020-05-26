package com.fanbo.mybaseadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var mPage =1
    var mAllData = ArrayList<String>()
    lateinit var mAdapter: MyStringAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
    }

    private fun initView() {
        srl_demo.isRefreshing=true
        mPage =1
    }

    private fun initData() {
        var manager = LinearLayoutManager(this)
        rlv_demo.layoutManager = manager
        getGoodsData()
        srl_demo.setOnRefreshListener {
            mPage =1
            mAllData.clear()
            if (mAdapter!=null){
                mAdapter.refreshData()
            }
            getGoodsData()
        }
        rlv_demo.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            private var isToUp = false
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var  lastItem = manager.findLastCompletelyVisibleItemPosition()
                if (lastItem==manager.itemCount -1 && isToUp){
                    mPage+=1
                    getGoodsData()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isToUp = dy > 0
            }
        })
    }

    private fun getGoodsData() {
        var strs = ArrayList<String>()
        if (mPage==1){
            for (i in 0..19){
                strs.add(""+i)
            }
        }else{
            for (s in (mPage-1)*20..mPage*20){
                strs.add("" + s)
            }
        }
        if (mPage<=5){
            mAllData.addAll(strs)
        }else{
            if (mAdapter!=null){
                mAdapter.adIsFinish()
            }
        }
        srl_demo.isRefreshing =false
        if (mPage==1){
            mAdapter = MyStringAdapter(this,R.layout.rlv_item_layout,mAllData)
            rlv_demo.adapter = mAdapter
        }else{
            Handler().postDelayed(Runnable {
                mAdapter.notifyDataSetChanged()
            },3000)

        }
    }
}
