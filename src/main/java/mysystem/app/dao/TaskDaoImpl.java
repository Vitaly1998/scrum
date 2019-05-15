package mysystem.app.dao;

import mysystem.app.models.State;
import mysystem.app.models.Task;
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
public class TaskDaoImpl implements TaskDao, CrudDao<Task>{

    @Autowired
    @Qualifier("searchDataSource")
    DataSource dataSource;


    @Autowired
    protected RowMapper<Task> taskRowMapper;

    protected NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Task model) {
        return false;
    }

    @Override
    public boolean update(Task model) {
        return false;
    }

    @Override
    public boolean delete(Task model) {
        return false;
    }

    @Override
    public List<Task> findAll() {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = null;
        try {
            tasks = jdbcTemplate.query(sql, sqlParameterSource, taskRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return tasks;
    }

    @Override
    public List<Task> getTasksForUser(User user) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("u_id", user.getId());
        String sql = "SELECT * FROM tasks where user_id = :u_id";
        List<Task> tasks = null;
        try {
            tasks = jdbcTemplate.query(sql, sqlParameterSource, taskRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return tasks;
    }

    @Override
    public boolean setTaskDone(Task task) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("t_id", task.getId());
        sqlParameterSource.addValue("t_state", State.DONE.toString());
        String sql = "UPDATE tasks set state = :t_state where id = :t_id";
        try {
             int i = jdbcTemplate.update(sql, sqlParameterSource);
             if (i == 0) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean selectTaskMyself(Task task, User user) {
        System.out.println(task.getId());
        System.out.println(user.getId());
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("t_id", task.getId());
        sqlParameterSource.addValue("t_state", State.INWORK.toString());
        sqlParameterSource.addValue("t_user_id", user.getId());
        String sql = "UPDATE tasks set state = :t_state, user_id = :t_user_id where id = :t_id";
        try {
            int i = jdbcTemplate.update(sql, sqlParameterSource);
            if (i == 0) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean newTask(Task task) {
        String sql = "INSERT INTO tasks (id, name, purpose, state, priority, user_id, author_id, project_id) VALUES  (:id, :name, :purpose, :state, :priority, :user_id, :author_id, :project_id)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", UUID.randomUUID().toString());
        mapSqlParameterSource.addValue("name", task.getName());
        mapSqlParameterSource.addValue("purpose", task.getPurpose());
        mapSqlParameterSource.addValue("state", task.getState().toString());
        mapSqlParameterSource.addValue("priority", task.getPriority().toString());
        mapSqlParameterSource.addValue("user_id", task.getUser_id() );
        mapSqlParameterSource.addValue("author_id", task.getAuthor_id() );
        mapSqlParameterSource.addValue("project_id", task.getProject_id());
        return jdbcTemplate.update(sql, mapSqlParameterSource) == 1;
    }
}
