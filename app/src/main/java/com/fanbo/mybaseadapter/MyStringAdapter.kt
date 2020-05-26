package com.fanbo.mybaseadapter

import android.content.Context
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

/**
 *  Created by Eason Time 2020/5/25
 *
 *  Describe:
 */
class MyStringAdapter<T>(mContext: Context, @LayoutRes mLayoutRes: Int, mDatas: List<T>): BaseRlVAdapter<T>(mContext,mLayoutRes,mDatas){

    var mDatas:List<T> = mDatas

    override fun bindViewData(holder: BaseViewHolder, pos: Int) {
        (holder.getView(R.id.tv_item_content) as TextView).text = mDatas.get(pos).toString()
    }
}