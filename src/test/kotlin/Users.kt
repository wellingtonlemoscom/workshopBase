import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetUsers {

    private val QUERY_PAGE = "page"
    private val QUERY_PER_PAGE = "per_page"
    private val URL_GET_USERS = "https://reqres.in/api/users"

    private fun getUsers(page:Int, per_page:Int) : Response {
        return Given {
            queryParam(QUERY_PAGE, page)
            queryParam(QUERY_PER_PAGE, per_page)
        } When {
            get(URL_GET_USERS)
        } Then {
            log().all()
        } Extract {
            response()
        }
    }

    @Test
    @DisplayName("Buscar 1 usuário com nome de George")
    fun getUsersTest() {
        val response = getUsers(1,1)

        assertAll("valida busca 1 usuário com nome de George",
            { assertNotNull(response) },
            { assertEquals(200, response.statusCode) },
            { assertEquals(1, response.jsonPath().getInt("page")) },
            { assertEquals("George", response.jsonPath().getString("data[0].first_name")) },
            { assertEquals(1, response.jsonPath().getInt("data[0].id")) }
        )
    }

    @Test
    @DisplayName("Valida posição de ID")
    fun getFiveUsersTest() {
        val response = getUsers(1, 5)
        val count  = response.jsonPath().getInt("data.size()")-1

        for(i in 0 .. count){
            assertEquals(i+1, response.jsonPath().getInt("data[$i].id"))
        }
    }
}