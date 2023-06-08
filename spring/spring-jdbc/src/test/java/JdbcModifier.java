import com.learn.spring.jdbc.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Config.class)
public class JdbcModifier {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        String sql = "INSERT INTO t_emp VALUES(NULL, ?, ?, ?)";
        Object[] objects = {"john", 3, "男"};
        int row = jdbcTemplate.update(sql, objects);
        System.out.println(row);
    }

    @Test
    public void testUpdate() {
        String sql = "UPDATE t_emp SET name = 'bob' WHERE id = 1";
        int row = jdbcTemplate.update(sql);
        System.out.println(row);
    }

    @Test
    public void testDelete() {
        String sql = "DELETE FROM t_emp WHERE id = ?";
        int row = jdbcTemplate.update(sql, 2);
        System.out.println(row);
    }
}
