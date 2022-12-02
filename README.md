# Homework说明
- 初始化用户sql的密码为：123
## 作业要求
- 在项目不变的基础上达到调用用户详情(/api/v1/user/{id})、用户列表(/api/v1/user)接口，正常返回数据；(https://docs.spring.io/spring-security/site/docs/5.4.6/reference/html5/#servlet-hello)
- 新增一个登陆接口
- 实现用户认证(JDBC Authentication\In-Memory Authentication\UserDetailsService+JPA) 
  资料参见：https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter、https://www.baeldung.com/spring-security-authentication-with-a-database
- 用户通过认证后能够获取到用户详情(/api/v1/user/{id})、用户列表(/api/v1/user)