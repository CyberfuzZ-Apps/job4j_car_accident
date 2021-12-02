package ru.job4j.accident.repository.jdbcrepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.Store;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Класс AccidentJdbcTemplate
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
/* @Repository */
public class AccidentJdbcTemplate implements Store<Accident> {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)";
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        int generatedAccidentId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        accident.setId(generatedAccidentId);
        linkRulesToAccidentInDB(accident);
    }

    @Override
    public void update(Accident accident) {
        jdbc.update(
                "UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        jdbc.update("DELETE FROM accident_rules WHERE accident_id = ?", accident.getId());
        linkRulesToAccidentInDB(accident);
    }

    private void linkRulesToAccidentInDB(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rules (accident_id, rules_id) VALUES (?, ?)",
                    accident.getId(), rule.getId());
        }
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM accident_rules WHERE accident_id = ?", id);
        jdbc.update("DELETE FROM accident WHERE id = ?", id);
    }

    @Override
    public Accident get(int id) {
        Accident accident = jdbc.queryForObject(
                "SELECT a.id, a.name, a.text, a.address, t.id AS typeId, t.name AS type FROM accident a "
                        + "JOIN types t ON t.id = a.type_id WHERE a.id = ?",
                getAccidentRowMapper(), id);
        if (accident == null) {
            return null;
        }
        getRulesForAccident(accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        Collection<Accident> rsl = jdbc.query(
                "SELECT a.id, a.name, a.text, a.address, t.id AS typeId, t.name AS type FROM accident a "
                        + "JOIN types t ON t.id = a.type_id",
                getAccidentRowMapper());
        for (Accident acc : rsl) {
            getRulesForAccident(acc);
        }
        return rsl;
    }

    private RowMapper<Accident> getAccidentRowMapper() {
        return (rs, row) -> {
            Accident newAccident = new Accident();
            newAccident.setId(rs.getInt("id"));
            newAccident.setName(rs.getString("name"));
            newAccident.setText(rs.getString("text"));
            newAccident.setAddress(rs.getString("address"));
            newAccident.setType(Type.of(
                    rs.getInt("typeId"),
                    rs.getString("type"))
            );
            return newAccident;
        };
    }

    private void getRulesForAccident(Accident acc) {
        Collection<Rule> rules = jdbc.query(
                "select * from rules join accident_rules on id = rules_id "
                        + "where accident_id = ?", (rs, row) -> Rule.of(
                                rs.getInt("id"),
                                rs.getString("name")
                        ), acc.getId());
        acc.setRules(new HashSet<>(rules));
    }

}
