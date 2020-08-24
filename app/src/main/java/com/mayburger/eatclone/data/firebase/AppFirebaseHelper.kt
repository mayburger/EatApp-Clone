package com.mayburger.eatclone.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mayburger.eatclone.model.UserDataModel
import com.mayburger.eatclone.util.constants.FirebaseConstants
import javax.inject.Inject


class AppFirebaseHelper @Inject constructor() : FirebaseHelper {


    private val TAG = this.javaClass.simpleName

    override fun createFirebaseUser(email: String, password: String): Task<AuthResult> {
        val mAuth = FirebaseAuth.getInstance()
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun createFirestoreUser(user: UserDataModel): Task<DocumentReference> {
        val db = Firebase.firestore
        return db.collection(FirebaseConstants.USERS)
            .add(user)
    }

    override fun checkFirestoreUser(email: String): Task<QuerySnapshot> {
        val db = Firebase.firestore
        return db.collection(FirebaseConstants.USERS).whereEqualTo(FirebaseConstants.EMAIL, email).get()
    }


}