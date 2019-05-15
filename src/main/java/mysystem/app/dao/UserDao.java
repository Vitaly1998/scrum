package mysystem.app.dao;


import mysystem.app.models.User;

public interface UserDao {
    boolean isLoginAvailable(String login);
    User getUser(String login);
    boolean isUserExist(User user);
    String getProjectId(String project);
    User getUserInSurname(String surname);
}
