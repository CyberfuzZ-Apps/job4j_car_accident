package ru.job4j.accident.repository.jdbcrepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

/**
 * Класс RuleJdbcTemplate
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Repository
public class RuleJdbcTemplate implements Store<Rule> {

    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Rule rule) {
        jdbc.update("INSERT INTO rules (name) VALUES (?)", rule.getName());
    }

    @Override
    public void update(Rule rule) {
        jdbc.update("UPDATE rules SET name = ? WHERE id = ?", rule.getName(), rule.getId());
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM rules WHERE id = ?", id);
    }

    @Override
    public Rule get(int id) {
        return jdbc.queryForObject(
                "SELECT * FROM rules WHERE id = ?",
                getRuleRowMapper(), id);
    }

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query("select * from rules",
                getRuleRowMapper());
    }

    private RowMapper<Rule> getRuleRowMapper() {
        return (rs, row) -> Rule.of(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
