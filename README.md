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
/**
 * 数据库用户管理实现类
 * */
public class UserDaoImpl extends DBUtil implements UserDao, AutoCloseable {
    // 将用户信息添加到数据库
    public boolean addUser(User user) throws Exception;
    // 将用户信息更改应用到数据库
    public boolean updateUser(User user) throws Exception;
    // 从数据库中删除用户信息
    public boolean deleteUser(int id) throws Exception;
    // 根据用户id在数据库中查询用户信息
    public User getUserById(int id) throws Exception;
    // 调取数据库中所有用户信息
    public List<User> getAllUser() throws Exception;
}
```