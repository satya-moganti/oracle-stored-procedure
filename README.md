# Stored Procedure with Spring boot and Java 17
This example project demonstrates   oracle stored procedure with custom object types

# Pre-requisites
1. Java installed version 17
2. Maven version >=3.8.8
3. Spring  : https://start.spring.io/  
4. Eclipse IDE  

# Requirement :
	Build the Application to store Book data using stored procure into Oracle Database

# Build : Oracle Stored Procedure application 
```java
 Step 1 : Download project from git repo from the following location and extract project
	       https://github.com/satya-moganti/oracle-stored-procedure.git
 Step 2 : Build a jar using following command
           cd oracle-stored-procedure
	         mvn clean install
 Step 3 : Run application using following command
           cd target
	         java -jar oracle-stored-procedure.jar	
```


# Implementation 
```java
  The Oracle Stored Procedure App Implemented using following technologies
    	1. Spring boot  for developing  REST API with validation  
     	2. Maven for dependency managemnt
    	3. Logger for logging information
    	4. Oracle Database (refered version - ojdbc8:21.1.0.0)
```
## Oracle Database  
```java
  Access Oracle Database using below URL
    JDBC URL : jdbc:oracle:thin:@localhost:1521:xe
    Username : system
    Password : root	
```

## Test Data to check REST API

   Use Postman and test using following data also adding script and 
   postman details in resource folder
   
```java
   Case 1 :  Create/Add Book  
	      URL : http://localhost:8888/books/createBook
	      Method : POST  
	      Request Body : 
	      {
			"bookid": 1,
			"title": "The Nova Princess",
			"author": "Rishi Alex",
			"publishedyear": "2023",
			"genre": "Love , Drama and Emotion",
			"price": 200
		  }
	      Response Body : Status - 200 
	      {
			"out_result": 1
		  }
```
```java
   Case 2 : Fetching Book details By Id
          URL :http://localhost:8888/books/1 
	      Method : GET  
	      Response body: 
	       {
			"bookid": 1,
			"title": "The Tales of Four Musketeers",
			"author": "Rishi Alex",
			"publishedyear": "2024",
			"genre": "Historical novel, adventure novel, swashbuckler",
			"price": 400
		   }
	   
```
```java
   Case 3 : Create or add list of books
	      URL : http://localhost:8888/books/createBooks
	      Method : POST  
	      Request Body : 
	      [
		   {
		   "bookid": 2,
		   "title": "The Tiger and Rexy",
		   "author": "Shannu Veer",
		   "publishedyear": "2024",
		   "genre": "Historical novel, adventure novel",
		   "price": 500
		   },
		  {
		   "bookid": 3,
		   "title": "The Moon and Nova",
		   "author": "Kushu Veer",
		   "publishedyear": "2024",
		   "genre": "Historical novel, adventure novel, scifi",
		   "price": 500
		  }
        ]
	    Response Body : Status - 200 
		  {
		   "out_result": 1
		  }
```
```java
   Case 3 : Fetching list of Books bY Ids
   	      URL : http://localhost:8888/books/booksList
	      Method : GET
		  Body: 
		  {
			"bookIdList": [2,3]
		  }
	      Response : Status - 200 
		  {
		   "out_book_list": 
			[
			 {
			  "bookid": 2,
			  "title": "The Tiger and Rexy",
			  "author": "Shannu Veer",
			  "publishedyear": "2024",
			  "genre": "Historical novel, adventure novel",
			  "price": 500
			 },
			 {
			  "bookid": 3,
			  "title": "The Moon and Nova",
			  "author": "Kushu Veer",
			  "publishedyear": "2024",
			  "genre": "Historical novel, adventure novel, scifi",
			  "price": 500
			 }
            ],
           "out_result": 1
          }
	      
```
### Reference Documentation
For further reference :

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.10/maven-plugin/reference/html/)
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/2.6.10/reference/htmlsingle/#documentation/)
* [Jdbc-Complex-Types](https://docs.spring.io/spring-framework/reference/data-access/jdbc/parameter-handling.html#jdbc-complex-types)
* [Oracle Download](https://www.oracle.com/database/technologies/xe-downloads.html)


### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Jdbc-Complex-Types](https://docs.spring.io/spring-framework/reference/data-access/jdbc/parameter-handling.html#jdbc-complex-types)
* [Modelling JDBC Operations](https://docs.spring.io/spring-framework/reference/data-access/jdbc/object.html)
* [PL SQL DB Fundamentals](https://docs.oracle.com/en/database/oracle/oracle-database/21/lnpls/plsql-language-fundamentals.html)




