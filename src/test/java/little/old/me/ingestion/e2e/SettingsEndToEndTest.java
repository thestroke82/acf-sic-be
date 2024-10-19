package little.old.me.ingestion.e2e;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;



@QuarkusTest
public class SettingsEndToEndTest {

    @Test
    void when_call_settings_endpoint_return_id1() {

        given()
          .when().get("/settings")
          .then()
             .statusCode(200)
             .body("dataPath", is(notNullValue()));
    }

    @Test
    @TestTransaction
    public void given_valid_command_when_put_settings_then_return_updated_settings() {
        given()
                .body("{\"dataPath\":\"dataPath\"}")
                .header("Content-Type", "application/json")
                .when().put("/settings")
                .then()
                .statusCode(204);

         given()
          .when().get("/settings")
          .then()
             .statusCode(200)
             .body("dataPath", is("dataPath"));
    }
}
