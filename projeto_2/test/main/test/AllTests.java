package main.test;

import main.test.client.ClientServiceTest;
import main.test.produto.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProductServiceTest.class, ClientServiceTest.class})
public class AllTests {
}
