### HBNCrudConsoleApp

[![Build Status](https://app.travis-ci.com/jmoloko/HBNCrudConsoleApp.svg?branch=main)](https://app.travis-ci.com/jmoloko/HBNCrudConsoleApp)
[![codecov](https://codecov.io/gh/jmoloko/HBNCrudConsoleApp/branch/main/graph/badge.svg?token=O0DQU9I1AI)](https://codecov.io/gh/jmoloko/HBNCrudConsoleApp)

**Console CRUD application with Hibernate**

Console CRUD application that interacts with the database and allows you to perform all CRUD operations on entities:
* Team (id, name, Set < Developer > developers)
* Developer (id, firstName, lastName, Set < Skill > skills)
* Skill (id, name)

**Requirements:**
* MVC pattern.
* All CRUD operations for each entity
* To build a project to use Maven
* For interaction with a database - Hibernate
* For configuration Hibernate - Annotations 
* The database initialization must be implemented using Flyway 
* Service layer Applications covered with unit tests (JUNIT + MOCKITO)

**Technologies:** _Java, PostgreSQL, Hibernate, Flyway, Maven, JUnit, Mockito, Travis_
