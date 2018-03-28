import static org.junit.Assert.*;

import org.junit.Test;


public class BonjourServiceTest {
	HelloService helloService = new HelloServiceImpl(new MockBonjourDaoImpl());
	
    @Test
    public void defaultGetBonjourMonde() {
        String helloString = helloService.hello();
        assertEquals("Bonjour Monde!", helloString);
    }
     
    @Test
    public void inputClassSaysBonjourMondeClass() {
        String helloString = helloService.hello("Class");
        assertEquals("Bonjour Monde, Class!", helloString);
    }
     
    @Test
    public void inputAgainSaysBonjourMondeAgain() {
        String helloString = helloService.hello("Again");
        assertEquals("Bonjour Monde, Again!", helloString);
    }

}