mvn clean

mvn install

docker build -t janfranco/spring-hibernate-demo .

docker stop spring-hibernate

docker run --rm -p 9000:8080 --name "spring-hibernate" janfranco/spring-hibernate-demo
