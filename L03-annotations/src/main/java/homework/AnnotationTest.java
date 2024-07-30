package homework;

public class AnnotationTest {
    @Before
    public void init() {
        System.out.println("before method");
    }

    @Test
    public void firstTest() {
        System.out.println("first test");
    }

    @Test
    public void secondTest() {
        System.out.println("second test");
        throw new IllegalArgumentException();
    }

    @After
    public void end() {
        System.out.println("after method");
    }
}
