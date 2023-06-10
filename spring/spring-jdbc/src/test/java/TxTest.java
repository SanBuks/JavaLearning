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
    void TestBookControllerBuyBook () {
        bookController.buyBook(1, 1);
    }

    @Test
    void TestBookControllerBuyBooks () {
        bookController.buyBooks(1, 1, 3);
//        bookController.buyBooks(1, 1, 3);
//        bookController.buyBooks(1, 1, 2);
    }
}
