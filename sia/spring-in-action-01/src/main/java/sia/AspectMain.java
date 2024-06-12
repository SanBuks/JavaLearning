package sia;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sia.domain.interfaces.Knight;

public class AspectMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/minstrel.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
