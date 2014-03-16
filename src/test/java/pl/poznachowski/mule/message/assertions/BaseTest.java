package pl.poznachowski.mule.message.assertions;

import static org.junit.rules.ExpectedException.none;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class BaseTest {

	@Rule
	public ExpectedException thrown = none();

	public BaseTest() {
		super();
		thrown.handleAssertionErrors();
	}

	protected void expectException(Class<? extends Throwable> type, String message) {
		thrown.expect(type);
		thrown.expectMessage(message);
	}
}
