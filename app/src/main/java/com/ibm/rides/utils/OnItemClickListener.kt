package com.ibm.rides.utils

import android.view.View

interface OnItemClickListener : View.OnClickListener {
    fun onClick(view: View?, position: Int)
}