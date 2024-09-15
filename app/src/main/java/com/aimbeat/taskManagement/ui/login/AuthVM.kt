package com.aimbeat.taskManagement.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(
    val registerModel: RegisterModel,
    var sharedPreferences: SharedPreferences,
    var editor: SharedPreferences.Editor,
    val firestore: FirebaseFirestore
) : ViewModel() {

    var _loginData = MutableLiveData<Int>()
    val logindata: LiveData<Int> get() = _loginData


    //  Register Validation checking ...
    fun registerAuth() {
        if (registerModel.email.isNullOrEmpty() || registerModel.password.isNullOrEmpty()) {
            return
        }
        val regiterData =
            hashMapOf("email" to registerModel.email, "password" to registerModel.password)

        storeData(regiterData)

    }

    // Login Validation checking...
    fun loginAuth() {
        if (registerModel.email.isNullOrEmpty() || registerModel.password.isNullOrEmpty()) {
            return
        }
        val loginData =
            hashMapOf("email" to registerModel.email, "password" to registerModel.password)

        checkLogin()

    }

    private fun checkLogin() {
        firestore.collection("user").whereEqualTo("email", registerModel.email).get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (document in it) {
                        if (document.getString("password").equals(registerModel.password)) {
                            _loginData.value = 0
                            editor.putString("email", document.getString("email")).apply()
                        } else {
                            _loginData.value = 1
                        }
                    }
                } else {
                    _loginData.value = 1
                }
                Log.v("checkLogin : ", "SUCCESS : " + it)
            }.addOnFailureListener {
                Log.v("checkLogin : ", "FAILURE : " + it)
            }

    }


    // Store Data On Firebase
    fun storeData(regiterData: HashMap<String, String>) {
        firestore.collection("user").add(regiterData).addOnSuccessListener {
            Log.v("storeData : ", "SUCCESS : " + it)
        }
            .addOnFailureListener {
                Log.v("storeData : ", "FAILURE : " + it)
            }
    }

}