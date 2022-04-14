package com.rahilkarim.vyapar.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.rahilkarim.vyapar.BuildConfig
import com.rahilkarim.vyapar.R
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

public class GlobalClass private constructor(mcontext: Context) {

    private var context: Context = mcontext
    private var preferences: SharedPreferences = mcontext.getSharedPreferences(
        mcontext.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    companion object {
        @Volatile
        private var INSTANCE: GlobalClass? = null

        fun getInstance(context: Context): GlobalClass {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = GlobalClass(context)
                }
            }

            return INSTANCE!!
        }
    }

    //todo preferences
    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun getIntWithD(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun setIntData(key: String?, setvalue: Int) {
        preferences.edit().putInt(key, setvalue).apply()
    }

    fun getString(key: String): String {
        return preferences.getString(key, "")!!
    }

    fun getStringWithD(key: String, defValue: String): String {
        return preferences.getString(key, defValue)!!
    }

    fun setStringData(key: String?, setvalue: String) {
        preferences.edit().putString(key, setvalue).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun setBooleanData(key: String?, setvalue: Boolean) {
        preferences.edit().putBoolean(key, setvalue).apply()
    }

    fun clearAllPreference() {
        preferences.edit().clear().commit()
    }

    public fun log(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    public fun toastshort(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    public fun toastlong(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    public fun hideKeyboard(view: Activity) {
        val view = view.currentFocus
        if (view != null) {
            val inputManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun changedateTimeFormat(
        defaultPattern: String,
        neededPattern: String,
        input: String?
    ): String? {

//        String inputPattern = "yyyy-mm-dd";
//        String outputPattern = "dd/mm/yyyy";
        val inputFormat = SimpleDateFormat(defaultPattern)
        val outputFormat = SimpleDateFormat(neededPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(input)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

    fun openDialer(context: Context, number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${number}")
        context.startActivity(intent)
    }

    fun openWhatsApp(context: Context, number: String) {

        try {
            val text: String
            text = "Regarding ${context.resources.getString(R.string.app_name)} app: \n"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=+91$number&text=$text")
            context.startActivity(intent)
        } catch (e: Exception) {
            val error = Log.getStackTraceString(e)
            log("openWhatsApp", error)
            toastlong("Unable to proceed, please try after sometime!")
        }
    }

    @SuppressWarnings("deprecation")
    fun fromHtml(html: String): Spanned? {
        if(html.isEmpty()){
            // return an empty spannable if the html is null
            return SpannableString("");
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(html);
        }
    }

    fun setTint(imageView: ImageView, colorRes: Int) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
    }

    fun debug(): Boolean {

        return BuildConfig.DEBUG
    }
}