package reflection.example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    @Test
    public void publicUse() throws Exception {
        // 1. 获取对象
        Class<User> clazz = User.class;
        User user = clazz.newInstance();

        // 2. 获取共有属性
        Field ageField = clazz.getField("age");
        ageField.set(user, 3);
        System.out.println(ageField.get(user));

        // 3. 调用共有函数
        Method showMethod = clazz.getMethod("show");
        showMethod.invoke(user);
    }

    @Test
    public void privateUse() throws Exception {

    }
}
