mvn clean

mvn install

docker build -t janfranco/spring-mvc-demo .

docker stop springmvc

docker run --rm -p 9000:8080 --name "springmvc" janfranco/spring-mvc-demo
