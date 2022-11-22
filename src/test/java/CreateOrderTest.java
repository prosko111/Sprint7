import api.client.OrdersClient;
import api.order.CreateOrder;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends SetupTests {
    private static final String API_ORDERS_CREATE = "api/v1/orders";
    private final CreateOrder order;
    private final List<String> colors;

    public CreateOrderTest(List<String> colors) {
        this.order = new CreateOrder("Alesha",
                "Zyravlev",
                "Zykovskogo 12",
                5,
                "+7 555 222 14 22",
                5,
                "2022-11-20",
                "Pozvonite");
        this.colors = colors;
    }


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new ArrayList<>(List.of("BLACK"))},
                {new ArrayList<>(List.of("GRAY"))},
                {new ArrayList<>(Arrays.asList("GRAY", "BLACK"))},
                {new ArrayList<>()}
        };
    }


    @Test
    @DisplayName("Создание заказа, и проверка обяз полей")
    public void createOrderParamColorsSuccessMessage() {
        OrdersClient ordersClient = new OrdersClient();
        order.setColor(colors);
        ordersClient.getCorrectNewOrder(order)
                .then()
                .statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}
