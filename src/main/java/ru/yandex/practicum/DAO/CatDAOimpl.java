package ru.yandex.practicum.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.JDBC.JDBCconnectionService;
import ru.yandex.practicum.model.Cat;

import java.util.Optional;

@Component
public class CatDAOimpl implements CatDAO {

    private final JdbcTemplate jdbcTemplate;

    public CatDAOimpl(JDBCconnectionService jdbCconnectionService) {
        this.jdbcTemplate = jdbCconnectionService.getTamplate();
    }


    @Override
    public Optional<Cat> findCatsById(String id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from cat_user where id =?",id);
        if (rowSet.next()) {
            Cat cat = new Cat(rowSet.getInt("id"),
                    rowSet.getString("nickname"),
                    rowSet.getString("username" ));
            return Optional.of(cat);
        }
        return Optional.empty();
    }
}
