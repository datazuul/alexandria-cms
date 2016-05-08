Alexandria CMS
==============

The Alexandria CMS.

Technologies used:

* Overall: Java, Spring, Spring Security
* Frontend: Spring MVC, Thymeleaf
* Business: Java
* Backend: JPA/Hibernate, Flyway

Features:

* Automatic admin user wizard.
* Multilingual GUI.
* Login/logout.
* User management (CRUD)
* Session logging incl. AOP-logging
* Layer modularization (Frontend, Business, Backend; each API and IMPL)

* Maven Site

Installation
============

1.  Install PostgreSql:

    on Ubuntu:

        $ apt-cache search postgresql
        ...
        postgresql - object-relational SQL database (supported version)
        postgresql-9.4 - object-relational SQL database, version 9.4 server
        ...
        $ sudo apt-get install postgresql


2.  Create a database on your PostgreSql instance:

        $ sudo su - postgres
    
        ($ dropdb 'alexandria_cms_db')
    
        $ psql -c "CREATE USER alexandria PASSWORD 'somepassword';"
    
        CREATE ROLE
    
        $ createdb alexandria_cms_db -O alexandria

    Check:

    List databases:

        $ psql -l
                                               List of databases
               Name        |   Owner    | Encoding |   Collate   |    Ctype    |   Access privileges   
        -------------------+------------+----------+-------------+-------------+-----------------------
         alexandria_cms_db | alexandria | UTF8     | de_DE.UTF-8 | de_DE.UTF-8 | 
         postgres          | postgres   | UTF8     | de_DE.UTF-8 | de_DE.UTF-8 | 
         template0         | postgres   | UTF8     | de_DE.UTF-8 | de_DE.UTF-8 | =c/postgres          +
                           |            |          |             |             | postgres=CTc/postgres
         template1         | postgres   | UTF8     | de_DE.UTF-8 | de_DE.UTF-8 | =c/postgres          +
                           |            |          |             |             | postgres=CTc/postgres
        (4 rows)

    List tables of database alexandria_cms_db:

        $ psql -d alexandria_cms_db
        psql (9.4.1)
        Type "help" for help.

        alexandria_cms_db=# \d
        No relations found.
        alexandria_cms_db=# \q

3. Put your database properties into configuration file(s):

        $ cd <CMS source directory>
        $ vi alexandria-cms-backend-impl-jpa/src/main/resources/de/alexandria/cms/config/SpringConfigBackend-<profile>.properties

        database.name=alexandria_cms_db
        database.hostname=localhost
        database.password=somepassword
        database.port=5432
        database.username=alexandria

Build
=====

Build CMS:

    $ cd <WMS source directory>
    $ mvn clean install

Usage
=====

Run CMS (in development)
 
    $ cd frontend
    $ mvn jetty:run

Run CMS (in production)

* Deploy WAR to Tomcat
* Start with java environment variable "spring.profiles.active" set to "PROD" (-Dspring.profiles.active:local)

Use CMS

    Browser: http://localhost:9898

CMS connects to database and if no admin user exists, the admin user creation assistant is launched.
Create an admin user.