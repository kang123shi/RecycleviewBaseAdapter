package com.fanbo.mybaseadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by Eason Time 2020/5/25
 *
 *  Describe:
 */
abstract class BaseRlVAdapter<T>(mContext: Context, @LayoutRes mLayoutRes: Int, mDatas: List<T>): RecyclerView.Adapter<BaseViewHolder>(){

    var mContext :Context = mContext
    var mLayoutRes: Int = mLayoutRes
    var mData: List<T> = mDatas

    //footview的两种情况
    var holder_staute  =0
    var normal_state   =1
    var footview_state =2

    //判断活动是否已结束
    var isFinish =false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (viewType ==normal_state){
            return  BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutRes,parent,false))
        }else{
            return  FootViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_loadmore_lay,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return mData.size+1
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder.itemViewType==footview_state) {
            if (!isFinish){
                (holder.getView<TextView>(R.id.tv_loadstate)).setText("加载更多....")
            }else{
                (holder.getView<TextView>(R.id.tv_loadstate)).setText("加载完成")
            }
        } else {
            bindViewData(holder, position)
        }
    }

    abstract fun bindViewData(holder: BaseViewHolder, pos: Int)

    override fun getItemViewType(position: Int): Int {
        if (position==mData.size){
            holder_staute = footview_state
        }else{
            holder_staute = normal_state
        }
        return holder_staute
    }

    //这里是数据加载完成
    fun adIsFinish(){
        isFinish =true
        notifyDataSetChanged()
    }

    //这里是重新刷新数据
    fun refreshData(){
        isFinish =false
        notifyDataSetChanged()
    }
    inner class FootViewHolder(itemView: View):BaseViewHolder(itemView)
}