import java.util.Scanner;

public class Login {
    private String userName = "tomoki";;
    private String password = "12345";
    public void run(){
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter Username : ");
        String _username = input1.next();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter Password : ");
        String _password = input2.next();
        if(userName.equals(_username)&&password.equals(_password)){
            System.out.println("Access Granted! Welcome!");
        }
        else if(userName.equals(_username)){
            System.out.println("Password Incorrect @@");
        }
        else if (password.equals(_password)){
            System.out.println("Username Invalid @@");
        }
        else {
            System.out.println("Username and Password Invalid @@");
        }
    }
    public static void main(String[] args) {
        Login myLogin =  new Login();
        myLogin.run();
    }
}
