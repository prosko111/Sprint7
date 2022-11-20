import request.CreateCourier;
import request.LoginCourier;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class ApiHelper {
    private static final String API_CREATE_COURIER = "api/v1/courier";
    private static final String API_LOGIN_COURIER = "api/v1/courier/login";
    private static final String API_DELETE_COURIER = "api/v1/courier/";
    private static int getCourierId(LoginCourier courier){
        return given()
                .body(courier)
                .when()
                .post(API_LOGIN_COURIER)
                .then()
                .extract().jsonPath().get("id");
    }

    public static void deleteCourier(LoginCourier courier){
        int id = getCourierId(courier);
        given()
                .when()
                .delete(API_DELETE_COURIER + id);
    }

    public static void createCourier(CreateCourier courier){
        given()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER);
    }
    public static void deleteCourierIfExist(LoginCourier courier){
        Response response = given()
                .body(courier)
                .when()
                .post(API_LOGIN_COURIER);
        if (response.statusCode() == 200){
            deleteCourier(courier);
        }
    }
}
