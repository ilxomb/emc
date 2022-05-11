package com.barust.emc;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin()
@SecurityRequirement(name = "JWT token")
@Tag(name = "HelloController", description = "HelloController маълумотлари")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Тестовый.... " + new Date());
        return "Тестовый HTTP GET запрос. Hello World !!! v.3";
    }
}
/*
./mvnw package && java -jar target/emc-0.0.1-SNAPSHOT.jar

--Dockerfile
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/emc-0.0.1-SNAPSHOT.jar
WORKDIR /opr/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

>
docker build -t emc:0.0.1 .

docker images

docker run -d -p 8080:8080 -t emc:0.0.1

docker stop $(docker ps -a -q)

docker tag emc:0.0.1 shzd/emc:0.0.1

docker images

docker login -u shzd

docker push shzd/emc:0.0.1
 */
