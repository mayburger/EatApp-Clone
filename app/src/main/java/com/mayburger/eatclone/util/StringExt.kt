package com.mayburger.eatclone.util

fun isRegisterValid(email:String, password:String, confirmPassword:String, fullName:String, phoneNumber:String):Boolean{
    return (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && (password == confirmPassword) && phoneNumber.isNotEmpty() && fullName.isNotEmpty())
}

fun isLoginValid(email:String,password:String):Boolean{
    return (email.isNotEmpty() && password.isNotEmpty())
}