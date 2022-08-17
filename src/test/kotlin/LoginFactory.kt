class LoginFactory {

    var loginPojo = LoginPojo()

    fun registerSuccess() : LoginPojo {
        loginPojo.email = "eve.holt@reqres.in"
        loginPojo.password = "pistol"
        return loginPojo
    }

    fun registerUnsuccessful() : LoginPojo {
        loginPojo.email = "eve.holt@reqres.in"
        return loginPojo
    }

    fun registerUserNotFound() : LoginPojo{
        loginPojo.email = "teste@reqres.in"
        loginPojo.password = "pistol"
        return loginPojo
    }
}