import api.client.OrdersClient;
import api.order.CreateOrderResp;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrderListTest extends SetupTests {
    private static final String API_ORDERS_CREATE = "api/v1/orders";

    @Test
    @DisplayName("Запрос списка заказов")
    public void getOrdersListSuccess() {
        OrdersClient ordersClient = new OrdersClient();
        List<CreateOrderResp> orders = ordersClient.getListOrders()
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("orders", CreateOrderResp.class);
        orders.forEach(x -> Assert.assertNotNull(x.getId()));
        orders.forEach(x -> Assert.assertNotNull(x.getFirstName()));
        orders.forEach(x -> Assert.assertNotNull(x.getLastName()));
        orders.forEach(x -> Assert.assertNotNull(x.getPhone()));
    }
}
