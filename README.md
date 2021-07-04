# Course-App


### Build

### 1) Run MySQL instance via docker

```
 docker run -p 3306:3306 -d --name courseapp_mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
 ```
 
 ### 2) Run SpringBoot microservices in following order -> 
 
 Configserver,
 Eurekaserver,
 Authorizationserver,
 Zuulserver,
 Userservice,
 Courseservice
 
 ``` 
git clone https://github.com/SpectraPhantom/course-app.git

cd <nameOfMicroservice>
mvn spring-boot:run
 ```
 
 ### 3) Build and run client side app 
 
 ```
 cd client-side
 npm install
 ng serve
 ```
 
 ### Access to the application via following url
 
 ```
 localhost:4200
 ```
 
 ### Users starter credentials
 
 #### Role: User
 
 ```
username: user
password: user
 ```
 
 #### Role: Admin
 
 ```
 username: admin
 password: admin
 ```
 
 ## Microservices diagram
 
 ![alt text](https://i.imgur.com/8gWAeqO.png)
 
 
 ## Config Repository Url

```
https://github.com/SpectraPhantom/courseapp-config-repo
```
