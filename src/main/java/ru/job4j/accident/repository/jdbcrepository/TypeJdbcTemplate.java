package ru.job4j.accident.repository.jdbcrepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс TypeJdbcTemplate
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class TypeJdbcTemplate implements Store<Type> {

    private final JdbcTemplate jdbc;

    public TypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Type type) {
        jdbc.update("INSERT INTO types (name) VALUES (?)", type.getName());
    }

    @Override
    public void update(Type type) {
        jdbc.update("UPDATE types SET name = ? WHERE id = ?", type.getName(), type.getId());
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM types WHERE id = ?", id);
    }

    @Override
    public Type get(int id) {
        return jdbc.queryForObject(
                "SELECT * FROM types WHERE id = ?",
                getTypeRowMapper(), id);
    }

    @Override
    public Collection<Type> findAll() {
        return jdbc.query("select * from types",
                getTypeRowMapper());
    }

    private RowMapper<Type> getTypeRowMapper() {
        return (rs, row) -> Type.of(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
