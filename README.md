Spring Boot Blog REST API
This project is a simple blogging platform implemented using Spring Boot, providing basic API functionalities for managing blog posts, comments, user authentication, and authorization.

Table of Contents
Features
Technologies Used
Getting Started
Prerequisites
Installation
API Endpoints
Authentication
Authorization
Contributing
License
Features
User Authentication: Users can sign up, log in, and receive JWT tokens for accessing protected endpoints.
Post Management: CRUD operations for managing blog posts, including creating, updating, deleting, and retrieving posts.
Comment System: Users can create, update, delete, and retrieve comments on blog posts.
Role-based Access Control: Admins have exclusive access to certain endpoints, such as creating and deleting posts.
Technologies Used
Spring Boot: Framework for building Java applications.
Spring Security: Provides authentication and authorization support.
JSON Web Tokens (JWT): Used for stateless authentication.
Hibernate Validator: For validating request payloads.
Lombok: Library for reducing boilerplate code.
Java Persistence API (JPA): Standard specification for ORM in Java applications.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher
Maven
MySQL (or any other relational database supported by Spring Boot)
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/your-username/spring-boot-blog.git
Navigate to the project directory:
bash
Copy code
cd spring-boot-blog
Build the project using Maven:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
java -jar target/spring-boot-blog.jar
API Endpoints
The following endpoints are available:

Authentication:
POST /api/auth/login - User login
POST /api/auth/register - User registration
Posts:
GET /api/posts - Get all posts
GET /api/posts/{id} - Get post by ID
POST /api/posts - Create a new post (Admin only)
PUT /api/posts/{id} - Update post by ID (Admin only)
DELETE /api/posts/{id} - Delete post by ID (Admin only)
Comments:
GET /api/posts/{postId}/comments - Get comments for a post
GET /api/posts/{postId}/comments/{id} - Get comment by ID for a post
POST /api/posts/{postId}/comments - Create a new comment for a post
PUT /api/posts/{postId}/comments/{id} - Update comment by ID for a post
DELETE /api/posts/{postId}/comments/{id} - Delete comment by ID for a post
Authentication
Authentication is done using JSON Web Tokens (JWT). Users can obtain a token by logging in or registering. This token must be included in the Authorization header of subsequent requests to access protected endpoints.

Authorization
Role-based access control is implemented using Spring Security annotations. Certain endpoints are restricted to users with specific roles, such as ADMIN.

Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

License
This project is licensed under the MIT License - see the LICENSE file for details.
