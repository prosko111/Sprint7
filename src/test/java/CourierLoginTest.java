import api.client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends CourierCreateLoginBaseTest {
    private static final String API_LOGIN_COURIER = "api/v1/courier/login";
/*    @Before
    public void regCourier(){
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier);
    }*/

    @Test
    @DisplayName("Авторизация курьера, позитивный сценарий")
    public void loginCourierSuccessMessage() {
        CourierClient courierClient = new CourierClient();
        courierClient.createNewCourier(courier);
        courierClient.loginCourier(courier)
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера с несуществующим логином")
    public void loginCourierWrongLoginErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courier.setLogin("qwer");
        courierClient.loginCourier(courier)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера с неправильным паролем")
    public void loginCourierWrongPasswordErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courier.setPassword("qwer");
        courierClient.loginCourier(courier)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    public void loginCourierWithoutLoginErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courier.setLogin("");
        courierClient.loginCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    public void loginCourierWithoutPasswordErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courier.setPassword("");
        courierClient.loginCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    @DisplayName("Авторизация курьера с некорректным логином и паролем")
    public void loginCourierNotExistErrorMessage() {
        CourierClient courierClient = new CourierClient();
        courier.setLogin("qwer");
        courier.setPassword("qwer");
        courierClient.loginCourier(courier)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
