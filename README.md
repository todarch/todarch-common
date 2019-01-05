# Todarch Common

Common utilities, and solutions that might be shared among other applications.
Those can be usual Java stuff, or related to Spring.


## Rest Error Handling

Spring Boot provides default BasicErrorController,
which shows famous white label error page for 404, or 403 responses made from Browser and its own json response.
It might be better to provide a new one to handle all of them in uniform and restful way.

- [404](https://api.todarch.com/td/non-secured/abc)
- [403](https://api.todarch.com/td/api/abc)

```json
{
    "errorCode": "404",
    "errorMessage": "Not Found"
}
```

The functionality is enabled by default. Disable it if not needed.

```yml
todarch.resterrorcontroller.enabled: false
```

## Health Check

Provides simple heath check endpoint and build info about the deployment artifact.
Those might be obsolete if we start to use Spring Boot Actuator.

Although it has auto-configuration, it is not enabled by default.

```yml
todarch.healtcheck.endpoints.enabled: true
```

- [up](https://api.todarch.com/td/non-secured/up)
- [build info](https://api.todarch.com/td/non-secured/bi)

Build info configuration is passed through when Docker image is created. [ci](https://github.com/todarch/todarch-td/blob/master/.circleci/config.yml), [jib](https://github.com/todarch/todarch-parent/blob/master/pom.xml)
