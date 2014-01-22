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

Scenario: home page displays 10 spittles (defined in defaultSpittlesPerPage property)
Given Jack is a user with an account
And user Jack posted 15 spittles
When I navigate to the home page
Then I see 10 spittles on the home page

Scenario: home page displays spittles from different users
Given Jack is a user with an account
And Anna is a user with an account
And user Jack posted 3 spittles
And user Anna posted 5 spittles
When I navigate to the home page
Then I see 8 spittles on the home page
And 3 of the spittles belong to user Jack
And 5 of the spittles belong to user Anna