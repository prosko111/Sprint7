package api.client;

import api.order.CreateOrder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrdersClient {
    private static final String API_ORDERS_CREATE = "api/v1/orders";

    @Step("Запрос на создание нового заказа")
    public Response getCorrectNewOrder(CreateOrder newOrder) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(newOrder)
                .post(API_ORDERS_CREATE);
        response.then();
        return response;
    }

    @Step("Запрос на получение списка заказов")
    public Response getListOrders() {
        Response response = given()
                .header("Content-type", "application/json")
                .get(API_ORDERS_CREATE);
        response.then();
        return response;
    }

}
