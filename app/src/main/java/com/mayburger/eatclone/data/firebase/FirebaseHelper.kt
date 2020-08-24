package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.mayburger.eatclone.model.UserDataModel


interface FirebaseHelper {

    fun createFirebaseUser(email:String,password:String): Task<AuthResult>

    fun createFirestoreUser(user:UserDataModel):Task<DocumentReference>

    fun checkFirestoreUser(email:String):Task<QuerySnapshot>

}