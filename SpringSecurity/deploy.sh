mvn clean

mvn install

docker build -t janfranco/spring-security .

docker stop spring-security

docker run --rm -p 9000:8080 --name "spring-security" --network=spring janfranco/spring-security

# docker network connect spring spring-crud
