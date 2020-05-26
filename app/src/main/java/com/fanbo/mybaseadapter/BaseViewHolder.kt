package com.fanbo.mybaseadapter

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by Eason Time 2020/5/25
 *
 *  Describe:
 */
open class BaseViewHolder(@NonNull itemView: View):RecyclerView.ViewHolder(itemView) {

    var mViews: SparseArrayCompat<View> = SparseArrayCompat()

    fun <V: View> getView(@IdRes res:Int):V{
        var view = mViews.get(res)
        if (view==null){
            view = itemView.findViewById(res)
            mViews.put(res,view)
        }
        return view as V
    }

}