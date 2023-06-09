package indi.mofan.matcher;

/**
 * @author mofan 2020/12/24
 */
public class UserService {

   public String find(String name) {
       UserDao userDao = new UserDao();
        return userDao.queryByName(name);
   }
}
