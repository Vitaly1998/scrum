package mysystem.app.dao;

import mysystem.app.models.Project;
import mysystem.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import java.util.List;
import java.util.UUID;


@Repository
public class UserDaoImpl implements UserDao, CrudDao<User> {

    @Autowired
    @Qualifier("searchDataSource")
    DataSource dataSource;

    @Autowired
    protected RowMapper<User> userRowMapper;

    @Autowired
    protected RowMapper<Project> projectRowMapper;


    protected NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public boolean isLoginAvailable(String login) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("user_login", login);
        String sql = "SELECT * FROM users where login = :user_login";
        try {
            jdbcTemplate.queryForObject(sql, sqlParameterSource, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("user_login", login);
        String sql = "SELECT * FROM users where login = :user_login";
        User us = null;
        try {
            us = jdbcTemplate.queryForObject(sql, sqlParameterSource, userRowMapper);
        }  catch (EmptyResultDataAccessException e) {
            return null;
        }
        return us;
    }

    public boolean isUserExist(User user) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("user_login", user.getLogin());
        String sql = "SELECT * FROM users where login = :user_login";
        try {
            User us = jdbcTemplate.queryForObject(sql, sqlParameterSource, userRowMapper);
            if (new BCryptPasswordEncoder().matches(user.getPassword(), us.getPassword())) {
                return true;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return false;
    }

    @Override
    public String getProjectId(String project) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("project", project);
        String sql = "SELECT * FROM projects where name = :project";
        Project proj;
        try {
            proj = jdbcTemplate.queryForObject(sql, sqlParameterSource, projectRowMapper);
            return proj.getId();
        } catch (EmptyResultDataAccessException e) {
            return saveProject(project);
        }
    }

    @Override
    public User getUserInSurname(String surname) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("surname", surname);
        String sql = "SELECT * FROM users where surname = :surname";
        List<User> us = null;
        try {
            us = jdbcTemplate.query(sql, sqlParameterSource, userRowMapper);
        }  catch (EmptyResultDataAccessException e) {
            return null;
        }
        return us.isEmpty()? null: us.get(0);
    }


    public String saveProject(String project) {
        String sql = "INSERT INTO projects (id, name) VALUES  (:id, :name)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String id = UUID.randomUUID().toString();
        mapSqlParameterSource.addValue("id", id);
        mapSqlParameterSource.addValue("name", project);
        return  jdbcTemplate.update(sql, mapSqlParameterSource) == 1? id: null;
    }

    @Override
    public boolean save(User model) {
        String sql = "INSERT INTO users (id, name, surname, role, login, password) VALUES  (:id, :name, :surname, :role, :login, :password)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", UUID.randomUUID().toString());
        mapSqlParameterSource.addValue("name", model.getName());
        mapSqlParameterSource.addValue("surname", model.getSurname());
        mapSqlParameterSource.addValue("role", model.getRole().toString());
        mapSqlParameterSource.addValue("login", model.getLogin());
        mapSqlParameterSource.addValue("password", new BCryptPasswordEncoder().encode(model.getPassword()));
        return jdbcTemplate.update(sql, mapSqlParameterSource) == 1;
    }

    @Override
    public boolean update(User model) {
        return false;
    }

    @Override
    public boolean delete(User model) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
