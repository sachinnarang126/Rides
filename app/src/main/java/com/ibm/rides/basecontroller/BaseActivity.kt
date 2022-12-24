package com.ibm.rides.basecontroller

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import com.ibm.rides.network.response.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    abstract fun getViewModel(): BaseAndroidViewModel?

    @Suppress("DEPRECATION")
    private var progressDialog: ProgressDialog? = null

    private var reLoginDialog: AlertDialog? = null

    private var retryAlert: AlertDialog? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        job = Job()
    }

    override fun onStart() {
        super.onStart()
        initAppScope(getViewModel())
    }

    fun initAppScope(vModel: BaseAndroidViewModel?) {
        vModel?.apply {
            message.observe(this@BaseActivity) {
                it?.run {
                    showSnackBar(this)
                    message.value = null
                }
            }

            networkResponse.observe(this@BaseActivity) {
                it?.let { status ->
                    when (status) {
                        Status.ERROR -> {
                            hideProgressDialog()
                        }
                        Status.LOADING -> {
                            showProgressDialog("Loading...")
                        }
                        Status.SUCCESS -> {
                            hideProgressDialog()
                        }
                    }
                    networkResponse.value = null
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        progressDialog?.dismiss()
        reLoginDialog?.dismiss()
        retryAlert?.dismiss()
    }

    fun showSnackBar(msg: String) {
        if (msg.isNotEmpty()) {
            val view: View = findViewById(android.R.id.content)
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        }
    }

    @Suppress("DEPRECATION")
    private fun showProgressDialog(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setCancelable(false)
            progressDialog?.isIndeterminate = false
            progressDialog?.setMessage(message)
            progressDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog?.show()
        } else if (!progressDialog!!.isShowing) {
            progressDialog?.setMessage(message)
            progressDialog?.show()
        }
    }

    open fun hideProgressDialog() {
        try {
            if (progressDialog?.isShowing == true) {
                progressDialog?.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}