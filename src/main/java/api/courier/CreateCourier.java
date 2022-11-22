package api.courier;

public class CreateCourier extends LoginCourier {
    private String firstName;

    public CreateCourier(String login, String password, String firstName) {
        super(login, password);
        this.firstName = firstName;
    }

    public CreateCourier() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
