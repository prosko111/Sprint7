import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import request.CreateOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
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
    public void createOrderParamColorsSuccessMessage() {
        order.setColor(colors);
        given()
                .body(order)
                .when()
                .post(API_ORDERS_CREATE)
                .then()
                .statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}
