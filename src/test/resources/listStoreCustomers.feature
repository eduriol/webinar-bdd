Feature: List store customers
In order to know the activity of my store
As a store manager
I want to get lists of customers signed up in a given date

	Scenario: the platform should provide a list of customers signed up the last Christmas Day
		Given we have the following customers signed up:
			| nombre	| apellido	| fecha de registro	|
			| Marty		| McFly		| 2018-01-10		|
			| Jennifer	| Parker	| 2018-01-10		|
			| Emmett	| Brown		| 2017-12-25		|
			| Biff		| Tannen	| 2015-08-31		|
		And I assigned them to my store
		When I request a list of customers signed up during the last Christmas Day
		Then I get the following customers:
			| nombre	| apellido	| fecha de registro	|
			| Emmett	| Brown		| 2017-12-25		|
