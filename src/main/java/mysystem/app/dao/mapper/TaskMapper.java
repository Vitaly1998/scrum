package mysystem.app.dao.mapper;

import mysystem.app.models.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getString("id"));
        task.setName(resultSet.getString("name"));
        task.setPurpose(resultSet.getString("purpose"));
        task.setState(State.valueOf(resultSet.getString("state").toUpperCase()));
        task.setPriority(Priority.valueOf(resultSet.getString("priority").toUpperCase()));
        task.setUser_id(resultSet.getString("user_id"));
        task.setAuthor_id(resultSet.getString("author_id"));
        task.setProject_id(resultSet.getString("project_id"));
        return task;
    }
}
