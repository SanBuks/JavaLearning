import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MagicConfig.class)
public class AmbiguityTest {
    @Test
    public void shouldNotBeNull() {
        assertTrue(context.containsBean("magicBean"));
    }
}
