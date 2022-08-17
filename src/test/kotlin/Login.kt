import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import java.util.HashMap

class Login {

    fun register01() {
        val jsonString: String =
            "{\n" +
                    "    \"email\": \"eve.holt@reqres.in\",\n" +
                    "    \"password\": \"pistol\"" +
                    "\n" +"}"

        Given {
            contentType(ContentType.JSON)
            body(jsonString)
            log().all()
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }


    fun register02() {
        val jsonHashMap: HashMap<String, String> = HashMap()
        jsonHashMap["password"] = "pistol"
        jsonHashMap["email"] = "eve.holt@reqres.in"

        Given {
            contentType(ContentType.JSON)
            body(jsonHashMap)
            log().all()
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }

    fun register03() {
        val loginPojo = LoginPojo()
        loginPojo.password = "pistol"
        loginPojo.email = "pistol"

        Given {
            contentType(ContentType.JSON)
            body(loginPojo)
            log().all()
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }

    fun register04(loginPojo: LoginPojo) {
        Given {
            contentType(ContentType.JSON)
            body(loginPojo)
            log().all()
        } When {
            post("https://reqres.in/api/register")
        } Then {
            log().all()
        }
    }

    private val loginFactory = LoginFactory()

    @Test
    fun registerTest() {
        register03()
    }

    @Test
    fun registerSuccessTest() {
        register04(loginFactory.registerSuccess())
    }

    @Test
    fun registerFailureTest() {
        register04(loginFactory.registerUnsuccessful())
    }

    @Test
    fun registerUserNotFound() {
        register04(loginFactory.registerUserNotFound())
    }
}