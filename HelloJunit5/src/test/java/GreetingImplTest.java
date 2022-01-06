import com.ronith.junit.Greetings;
import com.ronith.junit.GreetingsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreetingImplTest {
	
	private Greetings greeting;

	@BeforeEach
	public void setup() {
		
		greeting = new GreetingsImpl();
		
	}

	@Test
	public void greetShouldReturnValidOutput() {
		 String result = greeting.greet("Junit");
		 Assertions.assertNotNull(result);
		 Assertions.assertEquals("Hello Junit", result);
	}

	@Test
	public void greet_Should_Throw_Exception_For_NameNull() {
		Assertions.assertThrows(IllegalStateException.class, ()->{
			greeting.greet(null);
		});
	}
	
	@Test
	public void greet_Should_Throw_Exception_For_NameBlank() {
		Assertions.assertThrows(IllegalStateException.class, ()->{
			greeting.greet(null);
		});
	}
	
	@AfterEach
	public void teardown() {
		greeting = null;
	}
}
