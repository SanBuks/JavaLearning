import com.learn.spring.jdbc.Config;
import com.learn.spring.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Config.class)
public class TxTest {

    @Autowired
    private BookController bookController;

    @Test
    void TestBookController () {
        bookController.buyBook();
    }
}
