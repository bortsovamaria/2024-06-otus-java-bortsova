package homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationTest {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    @Before
    public void init() {
        logger.info("before method");
    }

    @Test
    public void firstTest() {
        logger.info("first test");
    }

    @Test
    public void secondTest() {
        logger.info("second test");
        throw new IllegalArgumentException();
    }

    @After
    public void end() {
        logger.info("after method");
    }
}
