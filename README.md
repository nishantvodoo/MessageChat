# MessageChat
Uses Java, Spring, Hibernate and MySQL. NO UI implementation

# To Run the project
#### 1: Clone the project in desktop and import using an IDE (Eclipse).
#### 2: Install MySQL database (5.7) and restore the the database from here.
#### 3: Update the MessageChat project properties file with your MySQL authentication info.
        spring.datasource.url=jdbc:mysql://localhost:3306/chatdatabase    % database name
        spring.datasource.username=root                                   % username to login to the mysql database
        spring.datasource.password=password                               % password to login to the mysql database
#### 4: Update maven dependencies, build and run the project.

# Project Setup
The project contains seven main package folder.
#### 1: com.nishant.third 
This is the entry point for the project where the spring and hibernate dependencies are injected.

#### 2: com.nishant.third.config
This is where the security (login) feature is defined. Here, I use the default Spring authentication.

#### 3: com.nishant.third.controllers
This is where the controllers or mapping for the endpoints are defined.

#### 4: com.nishant.third.extensions
This is where we define custom static methods. In this project this contains the validation methods for email and password.

#### 5: com.nishant.third.models
This is where we define our objects based on the database schema. 

#### 6: com.nishant.third.repository
This is where we define repositories for the project.

#### 7: com.nishant.third.service
This is where we expose different services.


# Available endpoints:
#### 1: To register a new user
http://localhost:8080/register

The registration is triggered from the LoginController using the registerUser() method. We supply dummy value to create a user. 
All new users are assigned a "User" role. Standard email and username format is validated. 

#### 2: To login
http://localhost:8080/login

The in-built Spring functionality is used to authenticate and log the user in. 
To login as ADMIN role use Username: "admin" and Password: "P@ssword1"
To login as User role use Username: "user" and Password: "P@ssword1"

#### 3: To list all users
http://localhost:8080/home/list

The list is triggered from the HomeController using the listUsers() method. We allow user with all roles ("Admin" or "User") to make this call.

#### 4: To search users based on search criteria
http://localhost:8080/home/searchUsers

The search is triggered from the HomeController using the searchUsers() method. We allow user with all roles ("Admin" or "User") to make this call.
The search accepts one or more criterias (username, email, firstname and lastname).
A dummy search criteria is provided at the searchUsers() method.

#### 5: To send a message to another user
http://localhost:8080/home/send

The send message is triggered from the HomeController using the sendMessage() method. The pre-condition is that the user who is logged in knows the receiver ID.
In this example we hardcode the receiver ID. However, we assume that the logged in user is the sender. This info is pulled in from the logged in user context.

#### 6: To view all messages received
http://localhost:8080/home/get

The get message is triggered from the HomeController using the getMessage() method. For this example, we retrieve all messages belonging to the user who is logged in.


