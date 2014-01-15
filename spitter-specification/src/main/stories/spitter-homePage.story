Browse home page.

Meta:
@category homePage

Narrative:

Navigating to the home page
As a user
I want to see fixed number of peoples thoughts properly ordered.

Scenario: home page displays all spittles
Given Jack is a user with an account
And user Jack posted 6 spittles
When I navigate to the home page
Then I see 6 spittles on the home page

Scenario: home page displays 25 spittles if defaultSpittlesPerPage property is not defined
Given Jack is a user with an account
And user Jack posted 30 spittles
And defaultSpittlesPerPage is not defined
When I navigate to the home page
Then I see 25 spittles on the home page

Scenario: home page displays limited number of spittles
Given Jack is a user with an account
And user Jack posted 6 spittles
And defaultSpittlesPerPage is 4
When I navigate to the home page
Then I see 4 spittles on the home page

Scenario: home page displays limited number of spittles from different users
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted 6 spittles
And user Anna posted 6 spittles
And defaultSpittlesPerPage is 10
When I navigate to the home page
Then I see 10 spittles on the home page
And 6 of the spittles belong to user Jack
And 4 of the spittles belong to user Anna

Scenario: home page displays limited number of spittles ordered by datetime descending
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted a spittle on '2013-12-25 23:00:00'
And user Jack posted a spittle on '2013-12-01 23:15:00'
And user Anna posted a spittle on '2013-12-21 23:00:00'
And user Anna posted a spittle on '2013-12-01 23:00:00'
And user Anna posted a spittle on '2013-12-15 23:00:00'
When I navigate to the home page
Then I see 5 spittles on the home page
And 2 of the spittles belong to user Jack
And 3 of the spittles belong to user Anna
And all spittles are ordered descending

