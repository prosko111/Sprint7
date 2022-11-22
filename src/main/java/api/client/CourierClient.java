package api.client;

import api.courier.CreateCourier;
import api.courier.LoginCourier;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String API_CREATE_COURIER = "api/v1/courier";
    private static final String API_LOGIN_COURIER = "api/v1/courier/login";
    private static final String API_DELETE_COURIER = "api/v1/courier/";

    @Step("Получение id курьера")
    private static int getCourierId(LoginCourier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(API_LOGIN_COURIER)
                .then()
                .extract().jsonPath().get("id");
    }

    @Step("Удаление курьера")
    public static void deleteCourier(LoginCourier courier) {
        int id = getCourierId(courier);
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(API_DELETE_COURIER + id);
    }

    @Step("Создание курьера")
    public Response createNewCourier(CreateCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(API_CREATE_COURIER);
        response.then();
        return response;
    }

    @Step("Удаление курьера если он существует")
    public static void deleteCourierIfExist(LoginCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(API_LOGIN_COURIER);
        if (response.statusCode() == 200) {
            deleteCourier(courier);
        }
    }

    @Step("Создание курьера с недостающими данными")
    public Response createIncorrectCourier(CreateCourier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(API_CREATE_COURIER);
    }

    @Step("Логин курьера")
    public Response loginCourier(LoginCourier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(loginCourier)
                .when()
                .post(API_LOGIN_COURIER);
    }
}
