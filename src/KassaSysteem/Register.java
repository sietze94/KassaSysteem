package KassaSysteem;

public class Register {
    private double register_cash;
    private int register_id;
    private ViewRegister view_register;
    private User current_user;

    Controller controller; // maybe this is needed ?

    // Constructor
    public Register(int id, double start_cash, Controller c){
        register_id = id;
        register_cash = start_cash;
        controller = c;
        view_register = new ViewRegister();
        controller.setView(view_register);
        // display a register view (inital view screen)

    }

    public void showRegisterView(){ view_register.showView(); }

    public double getRegisterCash(){return register_cash;}

    public void AddRegisterCash(double c){ register_cash += c; }

    public void withdrawRegisterCash(double c){ register_cash -= c; }

    public int getRegisterID(){return register_id;}

    public void setCurrentUser(User user){ current_user = user; }

}
