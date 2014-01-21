Spring MVC with Hibernate example project
=========================================

Summary
-------
A Twitter-like application called Spitter.
Based on Spring in Action, Third Edition by Craig Walls.
Using Gradle, Spring 3.2, Hibernate 3.6 and JBehave.


To run on embedded server
-------------------------

gradle clean tomcatRun

If you would like to test on the HSQL change in service-context.xml

<import resource="classpath:spring/database/dataSourceMySQL.xml"/>

to:

<import resource="classpath:spring/database/dataSourceHSQL.xml"/>


To test
-------

gradle test


To test the specification.
--------------------------

1. Run MySQL database server (you cannot test the embedded database with an embedded server - because of two different database instances).
2. Run embedded server
  gradle tomcatRun
3. In spitter-specification project:
a.) mvn test
b.) In intelliJ right click on the AnnotatedSpitterStoriesTest class and Run 'AnnotatedSpitterStoriesTest'.

TODO: use gradle to run the stories