Spring MVC with Hibernate example project
=========================================

Summary
-------
A Twitter-like application called Spitter.
Based on Spring in Action, Third Edition by Craig Walls.
Using Spring 3.2, Hibernate 3.6 and JBehave.


To run on embedded server
-------------------------

gradle clean tomcatRun


To test
-------

gradle test


To test the specification.
--------------------------

1. First run embedded server
  gradle tomcatRun
2. In intelliJ right click on the AnnotatedSpitterStoriesTest class and Run 'AnnotatedSpitterStoriesTest'.
Make sure your src/main/test directory is marked as 'Test Sources Root'.

TODO: use gradle to run the stories