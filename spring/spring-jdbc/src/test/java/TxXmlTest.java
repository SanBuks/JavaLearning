import com.learn.spring.txXml.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations = "classpath:tx.xml")
public class TxXmlTest {

    @Autowired
    private BookController bookController;

    @Test
    void TestBookControllerBuyBook () {
        bookController.buyBook(1, 1);
    }

}
