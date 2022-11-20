import org.junit.After;
import org.junit.Before;
import request.CreateCourier;

public class CourierCreateLoginBaseTest extends SetupTests{
    CreateCourier courier;
    @Before
    public void createCourier() {
        courier = new CreateCourier("Alesha", "3343", "AleshaName");
        ApiHelper.deleteCourierIfExist(courier);
    }

    @After
    public void deleteCourier() {
        ApiHelper.deleteCourierIfExist(courier);
    }

}
