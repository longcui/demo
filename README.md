## How to
- bootstrapped from:  [Building a RESTful Web Service
](https://spring.io/guides/gs/rest-service/)
- The controller and POJO class need to be under the same package as the Application class by default, otherwise might need to configure `SpringBootApplication` to have `scanBasePackage`
- Add keycloak adapter in pom.
    - Follow `KeycloakWebSecurityConfigurerAdapter`, set `keycloakConfigFileResource` to be `    @Value("${keycloak.configurationFile:WEB-INF/keycloak.json}")
`   - The keycloak.json could be downloaded from keycloak. [info.](https://www.springcloud.io/post/2022-02/spring-security-keycloak/#gsc.tab=0)



## INFO:
- `keycloak-spring-boot-starter` depends on spring boot 2.*, so I downgraded this project to 2.7
- [Keycloak doc v18](https://www.keycloak.org/docs/18.0/securing_apps/index.html#_spring_boot_adapter), I tried the `spring_boot_adapter`, have not gone to step 3, seems bit old fashion. eg: 
```
keycloak.securityConstraints[0].authRoles[0] = admin
keycloak.securityConstraints[0].authRoles[1] = user
keycloak.securityConstraints[0].securityCollections[0].name = insecure stuff
keycloak.securityConstraints[0].securityCollections[0].patterns[0] = /insecure

keycloak.securityConstraints[1].authRoles[0] = admin
keycloak.securityConstraints[1].securityCollections[0].name = admin stuff
keycloak.securityConstraints[1].securityCollections[0].patterns[0] = /admin
```
I then used the extending `KeycloakWebSecurityConfigurerAdapter` method
- [Spring Security, when using role-based authentication, requires that role names start with ROLE_. For example, an administrator role must be declared in Keycloak as ROLE_ADMIN or similar, not simply ADMIN.](https://www.keycloak.org/docs/18.0/securing_apps/index.html#_spring_security_adapter)


## TODO:
- [Deprecation of Keycloak adapters
](https://www.keycloak.org/2022/02/adapter-deprecation)

- [Another way](https://www.baeldung.com/spring-boot-keycloak) with diff dependencies to have keycloak adapter for spring boot