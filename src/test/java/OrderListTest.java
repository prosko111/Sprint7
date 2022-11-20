import org.junit.Assert;
import org.junit.Test;
import response.CreateOrderResp;

import java.util.List;

import static io.restassured.RestAssured.given;
public class OrderListTest extends SetupTests {
    private static final String API_ORDERS_CREATE = "api/v1/orders";
    @Test
    public void getOrdersListSuccess() {
        List<CreateOrderResp> orders = given()
                .when()
                .get(API_ORDERS_CREATE)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("orders", CreateOrderResp.class);
        orders.forEach(x-> Assert.assertNotNull(x.getId()));
        orders.forEach(x-> Assert.assertNotNull(x.getFirstName()));
        orders.forEach(x-> Assert.assertNotNull(x.getLastName()));
        orders.forEach(x-> Assert.assertNotNull(x.getPhone()));
    }
}
