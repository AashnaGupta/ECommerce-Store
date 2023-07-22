import java.util.ArrayList;

public class test {
    
    public static void main(String[] args) {
        test newTest = new test();
        test newTest2 = new test();

        ArrayList<test> testArray = new ArrayList<>();
        
        testArray.add(newTest);
        testArray.add(newTest2);

        System.out.println(testArray);
        
        testArray.remove(newTest);

        System.out.println(testArray);
    }
}
