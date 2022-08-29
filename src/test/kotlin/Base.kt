import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import java.util.HashMap

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class Base {

    @BeforeAll
    fun messageAll() {
        println("Rodando todos os testes em 1, 2, 3!")
    }

    @BeforeEach
    fun messageEach() {
        println("Rodando o teste logo em seguida")
    }

    fun specificationBase() : RequestSpecification {
        return RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("api")
            .build()
    }

    fun specificationBaseToken() : RequestSpecification {
        val token = getToken()

        return RequestSpecBuilder()
            .addRequestSpecification(specificationBase())
            .addHeader("Authorization", "Bearer $token")
            .build()
    }

    private fun getToken() : String {
        val loginJson: HashMap<String, String> = HashMap()
        loginJson.put("email", "eve.holt@reqres.in")
        loginJson.put("password", "cityslicka")

        return Given {
            spec(specificationBase())
            contentType(ContentType.JSON)
            body(loginJson)
        } When {
            post("login")
        } Extract {
            response().jsonPath().getString("token")
        }
    }

}