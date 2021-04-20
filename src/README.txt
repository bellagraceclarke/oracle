Oracle Analytics Coding Challenge - Vending Machine API

This component provides an API which will:

• Initialise the vending machine to a known state, for use when the machine is
set up. This should include setting the initial float (the coins placed in the
machine for customer change) which should be accepted as a parameter.

• Register coins that have been deposited by a user.

• Return the correct change to a user as coins when an order is received (a
parameter for the value of a product) and remove the coins from the
machine.

Testing:
Two cases have been added using JUnit to demonstrate how I would test a unit of code for production.
As the task specified an interactive command line testing harness, I did not do this comprehensively. The command line
program enables a user to test with their own values.

Design Decisions:
When writing production quality API I would consider using Spring to build a RESTful service.
However for this task a command line program that satisfied the requirements was sufficient.

Initially, I used floats for the monetary logic calculations, however when unexpected coins were being returned,
using the debugging tool I quickly realised that floats are stored in memory as base 2 (binary) digits, whereas monetary
values are using base 10.
One solution was to multiply all the floats by 10 and carry out the calculations in pence as integers.
However I decided to use Big Decimals so there was no need to convert between types. Also

The user is able to deposit coins one at a time, this was handled by writing an enum which contained the accepted coins.
The initial value is specified as a float and deposit individual coins, returning the change as coins.
The initial float does not have an impact on this. Therefore the initial float can be set to 0.0 and
the API would still run as intended.

To Run:
javac Main.java
java Main