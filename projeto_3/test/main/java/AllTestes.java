package main.java;
import main.java.services.ClientServiceTest;

import main.java.services.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClientServiceTest.class, ProductServiceTest.class})
public class AllTestes {

}
