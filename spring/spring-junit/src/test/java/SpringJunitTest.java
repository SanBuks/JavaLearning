import com.learn.spring.junit.Config;
import com.learn.spring.junit.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Config.class)
public class SpringJunitTest {

    @Autowired
    private User user;

    @Test
    public void test() {
        user.print();
    }
}
