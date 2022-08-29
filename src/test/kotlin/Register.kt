import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import java.util.HashMap

class Register : Base() {

    fun register01() {
        val jsonString: String =
            "{\n" +
                    "    \"email\": \"eve.holt@reqres.in\",\n" +
                    "    \"password\": \"pistol\"" +
                    "\n" +"}"

        Given {
            spec(specificationBase())
            contentType(ContentType.JSON)
            body(jsonString)
            log().all()
        } When {
            post("register")
        } Then {
            log().all()
        }
    }


    fun register02() {
        val jsonHashMap: HashMap<String, String> = HashMap()
        jsonHashMap["password"] = "pistol"
        jsonHashMap["email"] = "eve.holt@reqres.in"

        Given {
            spec(specificationBase())
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
        val registerPojo = RegisterPojo()
        registerPojo.password = "pistol"
        registerPojo.email = "pistol"

        Given {
            spec(specificationBase())
            contentType(ContentType.JSON)
            body(registerPojo)
            log().all()
        } When {
            post("register")
        } Then {
            log().all()
        }
    }

    fun register04(registerPojo: RegisterPojo) {
        Given {
            spec(specificationBaseToken())
            contentType(ContentType.JSON)
            body(registerPojo)
            log().all()
        } When {
            post("register")
        } Then {
            log().all()
        }
    }

    private val registerFactory = RegisterFactory()

    @Test
    fun registerTest() {
        register01()
    }

    @Test
    fun registerSuccessTest() {
        register04(registerFactory.registerSuccess())
    }

    @Test
    fun registerFailureTest() {
        register04(registerFactory.registerUnsuccessful())
    }

    @Test
    fun registerUserNotFound() {
        register04(registerFactory.registerUserNotFound())
    }
}