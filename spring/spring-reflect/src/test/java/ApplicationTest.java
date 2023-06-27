import com.learn.spring.reflect.bean.AnnotationApplicationContext;
import com.learn.spring.reflect.bean.ApplicationContext;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    public void test() {
        String packagePath = "com.learn.spring.reflect.bean";
//        ApplicationContext ctx = new AnnotationApplicationContext(packagePath);

        try {
            var list = AnnotationApplicationContext.getClassPath(packagePath);
            for (String item : list) {
                System.out.println(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
