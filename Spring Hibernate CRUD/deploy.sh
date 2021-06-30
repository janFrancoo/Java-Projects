mvn clean

mvn install

docker build -t janfranco/spring-crud .

docker stop spring-crud

docker run --rm -p 9000:8080 --name "spring-crud" --network=spring janfranco/spring-crud

# docker network connect spring spring-crud
