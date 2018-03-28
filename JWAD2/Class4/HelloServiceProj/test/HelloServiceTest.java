import static org.junit.Assert.*;

import org.junit.Test;


public class HelloServiceTest {
	HelloService helloService = new HelloServiceImpl(new MockHelloDaoImpl());
	
    @Test
    public void defaultGetHelloWorld() {
        String helloString = helloService.hello();
        assertEquals("Hello World!", helloString);
    }
     
    @Test
    public void inputClassSaysHelloWorldClass() {
        String helloString = helloService.hello("Class");
        assertEquals("Hello World, Class!", helloString);
    }
     
    @Test
    public void inputAgainSaysHelloWorldAgain() {
        String helloString = helloService.hello("Again");
        assertEquals("Hello World, Again!", helloString);
    }

}