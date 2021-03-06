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
Then I see 6 spittles

Scenario: home page displays 10 spittles (defined in defaultSpittlesPerPage property)
Given Jack is a user with an account
And user Jack posted 15 spittles
When I navigate to the home page
Then I see 10 spittles

Scenario: home page displays spittles from different users
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted 3 spittles
And user Anna posted 5 spittles
When I navigate to the home page
Then I see 8 spittles
And 3 of the spittles belong to user Jack
And 5 of the spittles belong to user Anna

Scenario: home page displays limited number of spittles ordered by datetime descending
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted a spittle on '25/12/2013 23:00:00'
And user Jack posted a spittle on '01/12/2013 23:15:00'
And user Anna posted a spittle on '21/12/2013 23:00:00'
And user Anna posted a spittle on '01/12/2013 23:00:00'
And user Anna posted a spittle on '15/12/2013 23:00:00'
When I navigate to the home page
Then I see 5 spittles
And 2 of the spittles belong to user Jack
And 3 of the spittles belong to user Anna
And all spittles are ordered descending

Scenario: spittles page displays spittles for a given user
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted 3 spittles
And user Anna posted 5 spittles
When I navigate to the home page
And click on username Jack
Then I see 3 spittles
And 3 of the spittles belong to user Jack

Scenario: can register a user
When I navigate to the registration page
And I put value Jack Black in field fullName
And I put value Jack in field username
And I put value mypassword in field password
And I submit registration form
Then I will be navigated to user page