import java.io.IOException;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class UserLocalTest {

    public static void main(String[] args) throws IOException {
        User test = new User("aysusaglam", "aysusaglam7@gmail.com", "12345");
        String expected = "aysusaglam,aysusaglam7@gmail.com,12345";
        String actual = test.toString();
        //testing toString() method
        if (expected.equals(actual)) {
            System.out.println("'User' toString() test passed!");
        } else {
            System.out.println("'User' toString() test failed");
        }
    }

}
