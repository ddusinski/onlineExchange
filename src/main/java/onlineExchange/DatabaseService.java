package onlineExchange;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public class DatabaseService {
    private static final Logger log = LoggerFactory.getLogger(DatabaseService.class);
    static private Connection conn = null;


    private JdbcTemplate jdbcTemplate;

    public DatabaseService() {
        setDataSource();
        createMySQLdb();
    }


    private void setDataSource() {
        //how to use aaplication.properties instead of this??
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_example");
        dataSource.setUser("springuser");
        dataSource.setPassword("ThePassword");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private void createMySQLdb() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS EXCHANGE_QUATATIONS ");
        jdbcTemplate.execute("CREATE TABLE EXCHANGE_QUATATIONS(id INT AUTO_INCREMENT, date VARCHAR(10), value DOUBLE , code VARCHAR(4), PRIMARY KEY (ID) );");
    }

    public void addQueries(String[] dates, double[] values, String code) {
        for (int i = 0; i < dates.length; i++) {
            jdbcTemplate.update("INSERT INTO EXCHANGE_QUATATIONS (date, value, code) VALUES (?,?,?)", new Object[]{dates[i], values[i], code});
        }
    }
    public double countAvgValue(){
        return jdbcTemplate.queryForObject("SELECT AVG(value) AS AVG_VALUE FROM EXCHANGE_QUATATIONS  ",double.class);
    }

    public double countMaxValue(){
        return jdbcTemplate.queryForObject("SELECT MAX(value) AS AVG_VALUE FROM EXCHANGE_QUATATIONS  ",double.class);
    }

    public double countMinValue(){
        return jdbcTemplate.queryForObject("SELECT MIN(value) AS AVG_VALUE FROM EXCHANGE_QUATATIONS  ",double.class);
    }

}
