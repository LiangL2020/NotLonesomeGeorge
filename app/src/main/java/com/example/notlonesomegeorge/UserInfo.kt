package com.example.notlonesomegeorge

class UserInfo {
    private var userEmail: String? = null
    private var userPassword: String? = null
    fun EmployeeInfo() {}

    fun getUserEmail(): String? {
        return userEmail
    }

    fun setUserEmail(userEmail: String) {
        this.userEmail = userEmail
    }

    fun getUserPassword(): String? {
        return userPassword
    }

    fun setUserPassword(userPassword: String) {
        this.userPassword = userPassword
    }
}