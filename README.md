
# Wedoogift backend challenge

This is a Sprint Boot application for the [wedoogift backend](https://gitlab.com/wedoogift-jobs/challenge/-/tree/master/backend).

This app was made with Java 11 using maven 3.x and a mysql server instance.

## How to Run

To run the application, follow the steps below :

* Clone this repository with Git. **Please use the master branch not the main branch. If the repository is cloned with the main branch, you can checkout to the master branch.**
* Make sure you are using the JDK 11 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package``` or `mvn clean install`
* Once successfully built, you can run the app (with tests) by using the following command in your terminal : `mvn spring-boot:run`

Once the application runs the terminal should look like this :
```
2022-12-20 18:14:40.891  INFO 8280 --- [  restartedMain] c.a.w.WedoogiftBackendApplication        : Started WedoogiftBackendApplication in 5.503 seconds (JVM running for 6.282)
2022-12-20 18:14:40.893  INFO 8280 --- [  restartedMain] c.a.wedoogiftbackend.util.DataInjector   : Chargement de données ...
Hibernate: 
    select
        company0_.company_id as company_1_0_,
        company0_.balance as balance2_0_,
        company0_.name as name3_0_
    from
        company company0_
2022-12-20 18:14:40.982  INFO 8280 --- [  restartedMain] c.a.wedoogiftbackend.util.DataInjector   : Chargement des données terminé.
```

## About the API

The API is a very simple, straight forward one. It's, as described in the original wedoogift challenge, an API to make companies able to make deposits to users and users to be able to calculate their total balance as well as other features.

The a api is a set of REST controllers. It uses an sql database (MySQL) to store the data. You can also do with an in-memory database like H2 by changing the database configuration in the application.properties file.

If your database connection properties work, you can call some REST endpoints defined in `com.allamou.wedoogiftbackend.controller.CompanyController` and `com.allamou.wedoogiftbackend.controller.UserController` on **port 8080**.

The two main endpoits are ```/companies/``` and ```/users/```.

Here are all the application endpoints that can be called :

### Getting all the users stored in the database
```
GET /api/users/ HTTP/1.1
Host: localhost:8080

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/api/users/
body : [{"userId":1,"fullName":"Dominique Simon","giftBalance":0.0,"mealBalance":0.0,"totalBalance":0.0},{"userId":2,"fullName":"Renaud Duchamps","giftBalance":0.0,"mealBalance":100.0,"totalBalance":100.0},...]
```

### Calculate user's balance

Parameters :
* `user_id` : the id of the user's balance to be calculated.
```
GET /api/users/balance?user_id=3 HTTP/1.1
Host: localhost:8080

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/api/users/balance?user_id=3
body : {"fullName":"Rosemonde Beaumont","giftBalance":240.5,"mealBalance":0.0,"totalBalance":240.5}
```
### Make a deposit
Parameters :
* `company_id` : the id of the company that'll give the deposit.
* `user_id` : the id of the user receiving the deposit.
* `amount` : the deposit amount to be given to the user by the company.
* `type` : the deposit type. `GIFT` or `MEAL`.
```
POST /api/companies/deposit?company_id=1&user_id=1&amount=200&type=GIFT HTTP/1.1
Host: localhost:8080

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/api/companies/deposit?company_id=1&user_id=1&amount=200&type=GIFT
```

## If you have any question here's my email address : allamouothmane50@gmail.com