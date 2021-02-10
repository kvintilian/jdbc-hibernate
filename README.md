# Решение к задаче [Безопасные методы](https://github.com/netology-code/jd-homeworks/blob/master/spring_method_security/task1/README.md) по теме Method security. OAuth2

* Для ```SecurityConfig``` добавлена аннотация ```@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)```
* Согласно заданию реализован новый контроллер ```PersonSecController```