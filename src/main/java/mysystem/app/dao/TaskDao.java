package mysystem.app.dao;

import mysystem.app.models.Task;
import mysystem.app.models.User;

import java.util.List;

public interface TaskDao {
    List<Task> getTasksForUser(User user);
    boolean setTaskDone(Task task);
    boolean selectTaskMyself(Task task, User user);
    boolean newTask(Task task);
}
