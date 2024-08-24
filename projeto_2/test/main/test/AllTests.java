package main.test;

import main.test.service.ClientServiceTest;
import main.test.service.FuncionarioServiceTest;
import main.test.service.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProductServiceTest.class, ClientServiceTest.class, FuncionarioServiceTest.class})
public class AllTests {
}
