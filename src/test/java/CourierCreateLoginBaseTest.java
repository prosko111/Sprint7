import api.client.CourierClient;
import api.courier.CreateCourier;
import api.courier.LoginCourier;
import org.junit.After;
import org.junit.Before;

public class CourierCreateLoginBaseTest extends SetupTests {
    CreateCourier courier;
    LoginCourier courierLoginCorrect;
    LoginCourier courierLoginInvalid;

    @Before
    public void createCourier() {
        courier = new CreateCourier("Alesha", "3343", "AleshaName");
        CourierClient.deleteCourierIfExist(courier);
    }

/*    public void loginInvalid() {
        courierLoginInvalid = new LoginCourier("Alesha", "");
    }*/

    @After
    public void deleteCourier() {
        CourierClient.deleteCourierIfExist(courier);
    }

}
