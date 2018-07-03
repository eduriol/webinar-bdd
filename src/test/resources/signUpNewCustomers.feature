Feature: Sign up new customers from the store
In order to fidelize customers
As a store assistant
I want to sign up new customers

	Scenario: new customers should start with 100 points
		Given Marty McFly wants to become a new customer
		When I sign up him in the platform
		Then he starts with 100 points
