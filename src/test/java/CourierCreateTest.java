import api.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class CourierCreateTest extends CourierCreateLoginBaseTest {
    private static final String API_CREATE_COURIER = "api/v1/courier";
    @Test
    @DisplayName("Получение успешного ответа = 201, на создание курьера")
    public void createCourierSuccessMessage() {
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier)
                .then()
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера с повторным логином")
    public void createCourierNotUniqueErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier);
        courierClient.createNewCourier(courier)
                .then()
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание курьера с недостающими данными, пустое поле логина")
    public void createCourierWithoutLoginErrorMessage() {
        courier.setLogin("");
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с недостающими данными, пустое поле пароль")
    public void createCourierWithoutPassErrorMessage() {
        courier.setPassword("");
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    
}
