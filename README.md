## How to
- Keycloak is running at: http://localhost:8088
- bootstrapped from:  [Building a RESTful Web Service
](https://spring.io/guides/gs/rest-service/)
- The controller and POJO class need to be under the same package as the Application class by default, otherwise might need to configure `SpringBootApplication` to have `scanBasePackage`
- [tutorial](https://www.keycloak.org/2017/05/easily-secure-your-spring-boot.html)
- Method 1:
    - Add keycloak-spring-boot-starter in pom.
    - if using OAuth2 "Service Account/Client Credential"
        - in application.properties:
        ```js
        keycloak.realm = Dev-idc
        keycloak.auth-server-url = http://localhost:8088
        #keycloak.auth-server-url = https://auth.ops.kognitwin.cn/


        keycloak.resource = kspice-adapter-service-account

        keycloak.bearer-only= true

        keycloak.security-constraints[0].authRoles[0]=user
        keycloak.security-constraints[0].securityCollections[0].patterns[0]=/*
        ```
        - Set `Service Account Roles` to contain `user` for client: `Kspice-adapter-service-account `
        - Set `Authorization Enabled` for client: `Kspice-adapter-service-account ` 
        - Use Postman OAuth2 to test
     



- Method 2 (having little problem):  
    - Add spring-boot-starter-security in pom.
    - Add keycloak-spring-boot-starter in pom?
    - Follow `KeycloakWebSecurityConfigurerAdapter`, set `keycloakConfigFileResource` to be `    @Value("${keycloak.configurationFile:WEB-INF/keycloak.json}"), this might be the little problem since spring-boot-starter-security still goes to application.properties ...
`   - The keycloak.json could be downloaded from keycloak. [info.](https://www.springcloud.io/post/2022-02/spring-security-keycloak/#gsc.tab=0)

- Since `spring-boot-maven-plugin` configuration executable is used. this app could be run by `./demo-0.0.1-SNAPTSHOT.jar` instead of `java -jar`
    - When transferred the file to Ubuntu, the file is in `rw-`, run `chmod +x demo ...` 
    - Run `nohup demo ...` to be no hangup app(run in background even after Shell closes)



## INFO:
- `keycloak-spring-boot-starter` depends on spring boot 2.*, so I downgraded this project to 2.7
- [Keycloak doc v18](https://www.keycloak.org/docs/18.0/securing_apps/index.html#_spring_boot_adapter), I tried the `spring_boot_adapter`, seems bit old fashion. eg: 
``` js
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




