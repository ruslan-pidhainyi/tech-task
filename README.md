# tech-task
run it using 
#mvn spring-boot:run
(for that you will need mvn instaled and properly configured JAVA_HOME, java version 11 and higher)

or open it in IDE since you probably will want to check TC's anyway

server listens on the port 8080

GET http://localhost:8080/routing/CZE/ITA

suppose to return 

{"route":["CZE","AUT","ITA"]}
