package com.mayburger.eatclone.ui.auth.register

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.ext.isRegisterValid
import com.mayburger.eatclone.util.rx.SchedulerProvider

class RegisterViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<RegisterNavigator>(dataManager, schedulerProvider) {

    val email = ObservableField("")
    val password = ObservableField("")
    val confirmPassword = ObservableField("")
    val fullName = ObservableField("")
    val phoneNumber = ObservableField("")

    init {

    }

    fun onClickLogin() {
        navigator?.onClickLogin()
    }

    fun onClickRegister() {
        if (isRegisterValid(
                email.get() ?: "",
                password.get() ?: "",
                confirmPassword.get() ?: "",
                fullName.get() ?: "",
                phoneNumber.get() ?: ""
            )
        ) {
            navigator?.showLoading()
            email.get()
                ?.let { password.get()?.let { it1 -> dataManager.createFirebaseUser(it, it1) } }
                ?.addOnCompleteListener { auth ->
                    if (auth.isSuccessful) {
                        navigator?.hideLoading()
                        addUserToFirestore()
                    } else {
                        if (auth.exception is FirebaseAuthUserCollisionException) {
                            dataManager.getUserByEmail(email.get() ?: "").addOnCompleteListener {
                                if (it.result?.documents?.isEmpty()!!) {
                                    addUserToFirestore()
                                } else {
                                    navigator?.hideLoading()
                                    navigator?.onError(auth.exception?.message)
                                }
                            }
                        } else {
                            navigator?.hideLoading()
                            navigator?.onError(auth.exception?.message)
                        }
                    }
                }
        } else {
            navigator?.onError("Please make sure all the fields are filled and conditions are met")
        }
    }

    fun addUserToFirestore() {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email.get() ?: "", password.get() ?: "")
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                    dataManager.createFirestoreUser(
                        UserDataModel(
                            uid,
                            email.get(),
                            password.get(),
                            fullName.get(),
                            phoneNumber.get()
                        ), uid
                    ).addOnCompleteListener {
                        navigator?.hideLoading()
                        if (it.isSuccessful) {
                            FirebaseAuth.getInstance().signOut()
                            navigator?.onSuccessRegister()
                        } else {
                            navigator?.onError(it.exception?.message)
                        }
                    }
                }
            }
    }

    override fun onEvent(obj: Any) {

    }
}