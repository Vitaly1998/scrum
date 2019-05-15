package mysystem.app.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mysystem.app.dao.UserDaoImpl;
import mysystem.app.models.User;

@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;


    public boolean createUser(User user) {
        if (!userDao.isLoginAvailable(user.getLogin())) return false;
        return userDao.save(user);
    }

    public boolean checkUser(User user) {
        return userDao.isUserExist(user);
    }


}
