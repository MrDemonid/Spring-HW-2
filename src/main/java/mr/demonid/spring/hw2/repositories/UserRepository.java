package mr.demonid.spring.hw2.repositories;

import mr.demonid.spring.hw2.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class UserRepository implements IRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        return jdbc.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        });
    }

    @Override
    public User findById(int id)
    {
        String sql = "SELECT * FROM userTable WHERE id=?";

        return jdbc.query(sql,new Object[]{id}, new int[]{Types.INTEGER}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        }).stream().findFirst().orElse(new User());
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES(?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return findById(user.getId());
    }

}
