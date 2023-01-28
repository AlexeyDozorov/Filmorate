package ru.yandex.practicum.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.JDBC.JDBCconnectionService;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.Post;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
@Component
public class PostDAOImpl implements PostDAO{
    private final JdbcTemplate jdbcTemplate;

    public PostDAOImpl(JDBCconnectionService jdbCconnectionService) {
        this.jdbcTemplate = jdbCconnectionService.getTamplate();
    }

    @Override
    public Collection<Post> findByCat(Cat cat) {
        String sql = "SELECT * FROM cat_post " +
                "WHERE author_id = '" +cat.getId()+ "" +
                "' ORDER BY creation_date";
        return jdbcTemplate.query(sql, (rs, rowNum)-> makePost(cat, rs));
    }

    private Post makePost(Cat cat, ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setDescription(rs.getString("description"));
        post.setCat(cat);
        post.setPhotoUrl(rs.getString("photo_url"));
        post.setCreationDate(Integer.parseInt(rs.getString("creation_date")));
        post.setId(Integer.parseInt(rs.getString("id")));
        return post;
    }


}
