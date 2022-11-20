import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;


public class CourierCreateTest extends CourierCreateLoginBaseTest {
    private static final String API_CREATE_COURIER = "api/v1/courier";
    @Test
    public void createCourierSuccessMessage() {
        given()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER)
                .then()
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    public void createCourierNotUniqueErrorMessage() {
        ApiHelper.createCourier(courier);
        given()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER)
                .then()
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    public void createCourierWithoutLoginErrorMessage() {
        courier.setLogin("");
        ApiHelper.createCourier(courier);
        given()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void createCourierWithoutPassErrorMessage() {
        courier.setPassword("");
        given()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    
}
