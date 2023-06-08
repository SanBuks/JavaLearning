import com.learn.spring.jdbc.Config;
import com.learn.spring.jdbc.Emp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Config.class)
public class JdbcQuery {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery1() {
        String sql = "SELECT * FROM t_emp WHERE id = ?";

        Emp empResult = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    Emp emp = new Emp();
                    emp.setAge(rs.getInt("age"));
                    emp.setSex(rs.getString("sex"));
                    emp.setName(rs.getString("name"));
                    return emp;
                }, 1);

        System.out.println(empResult);
    }

    @Test
    public void testQuery2() {
        String sql = "SELECT * FROM t_emp WHERE id = ?";
        Emp empResult = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class),
                1);
        System.out.println(empResult);
    }

    @Test
    public void testQuery3() {
        String sql = "SELECT * FROM t_emp";
        List<Emp > empResults = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(empResults);
    }

    @Test
    public void testQuery4() {
        String sql = "SELECT COUNT(*) FROM t_emp";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(result);
    }
}
