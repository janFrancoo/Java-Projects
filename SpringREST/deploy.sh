mvn clean

mvn install

docker build -t janfranco/spring-rest .

docker stop spring-rest

docker run --rm -p 9000:8080 --name "spring-rest" --network=spring janfranco/spring-rest

# docker network connect spring spring-rest
