Java课程设计
===============

密码加密使用SHA-256，将输入加密后与数据库中存储的密文比较，提高安全性！

## 开发环境

- 语言：Java 17

- IDE(JAVA)： Eclipse安装lombok插件 或者 IDEA

- 依赖管理：Maven

- 数据库：MySQL8.0+




数据库管理实现类结构和接口：
```java
public class UserDaoImpl implements UserDao, AutoCloseable {
    public boolean addUser(User user) throws Exception;

    public boolean updateUser(User user) throws Exception;

    public boolean deleteUser(int id) throws Exception;

    public User getUserById(int id) throws Exception;

    public List<User> getAllUser() throws Exception;
}
```