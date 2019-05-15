package mysystem.app.sevices;


import mysystem.app.dao.TaskDaoImpl;

import mysystem.app.dao.UserDaoImpl;
import mysystem.app.dto.TaskDto;
import mysystem.app.models.Task;
import mysystem.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDaoImpl taskDao;
    @Autowired
    private UserDaoImpl userDao;


    public List<Task> getTasksForUser(String login) {
        User us = userDao.getUser(login);
        if (us!=null) {
            return taskDao.getTasksForUser(us);
        }
        else return null;
    }

    public List<Task> getAllTask() {
        return taskDao.findAll();
    }


    public boolean setDoneTask(Task task) {
        return taskDao.setTaskDone(task);
    }

    public boolean selectTaskMyself(Task task, String login) {
        User user = userDao.getUser(login);
        if (user != null) {
            return taskDao.selectTaskMyself(task, user);
        }
        return false;
    }

    public User getUser(String login) {
        return userDao.getUser(login);
    }

    public String getProjectId(String project) {
        return userDao.getProjectId(project);
    }

    public boolean isUserExist(String login) {
        return userDao.isUserExist(new User(login));
    }


    public boolean newTask(Task task){
        return taskDao.newTask(task);
    }

    public User getUserInSurname(String surname) {
        return userDao.getUserInSurname(surname);
    }
}
