mvn clean

mvn install

docker build -t janfranco/spring-aop .

docker stop spring-aop

docker run --rm -p 9000:8080 --name "spring-aop" --network=spring janfranco/spring-aop

# docker network connect spring spring-crud
