Browse home page.

Meta:
@category homePage

Narrative:

Navigating to the home page
As a user
I want to see fixed number of peoples thoughts properly ordered.

Scenario: home page displays all spittles
Given The number of users is: one
And user one posted 6 spittles
When I navigate to the home page
Then I see 6 spittles on the home page

Scenario: home page displays 25 spittles if defaultSpittlesPerPage property is not defined
Given The number of users is: one
And user one posted 30 spittles
And defaultSpittlesPerPage is not defined
When I navigate to the home page
Then I see 25 spittles on the home page

Scenario: home page displays limited number of spittles
Given The number of users is: one
And user one posted 6 spittles
And defaultSpittlesPerPage is 4
When I navigate to the home page
Then I see 4 spittles on the home page

Scenario: home page displays limited number of spittles from different users
Given The number of users is: two
And user one posted 6 spittles
And user two posted 6 spittles
And defaultSpittlesPerPage is 10
When I navigate to the home page
Then I see 10 spittles on the home page
And 6 of the spittles belong to user one
And 4 of the spittles belong to user two

Scenario: home page displays limited number of spittles ordered by datetime descending
Given The number of users is: two
And user one posted a spittle on '2013-12-25 23:00:00'
And user one posted a spittle on '2013-12-01 23:15:00'
And user two posted a spittle on '2013-12-21 23:00:00'
And user two posted a spittle on '2013-12-01 23:00:00'
And user two posted a spittle on '2013-12-15 23:00:00'
When I navigate to the home page
Then I see 5 spittles on the home page
And 2 of the spittles belong to user one
And 3 of the spittles belong to user two
And all spittles are ordered descending

