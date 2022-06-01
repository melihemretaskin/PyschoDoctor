package com.example.psychodoctor.data.local

import android.content.Context


class ClientPreferences(context: Context) {
    companion object{
        private const val PREFERENCES_NAME = "User"
        private const val DOC_ID = "UserEmail"
        private const val REMEMBER_ME = "RememberMe"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)
    }

    fun setDocId(userEmail : String){
        with(sharedPreferences.edit()){
            userEmail.let {
                putString(DOC_ID,it)
                apply()
            }
        }
    }

    fun getDocId() = sharedPreferences.getString(DOC_ID,"")

    fun clearSharedPref(){
        with(sharedPreferences.edit()){
            clear()
            apply()
        }
    }


    fun setRememberMe(state : Boolean){
        with(sharedPreferences.edit()){
            putBoolean(REMEMBER_ME,state)
            apply()
        }
    }

    fun isRememberMe() = sharedPreferences.getBoolean(REMEMBER_ME,false)
}