package ru.javawebinar.topjava.service;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.*;
import org.slf4j.bridge.*;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.*;
import org.springframework.test.context.junit4.*;
import ru.javawebinar.topjava.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {
    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }
}