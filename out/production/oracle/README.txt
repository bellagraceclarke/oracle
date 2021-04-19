Oracle Analytics Coding Challenge - Vending Machine API
This component provides an API which will:

• Initialise the vending machine to a known state, for use when the machine is
set up. This should include setting the initial float (the coins placed in the
machine for customer change) which should be accepted as a parameter.

• Register coins that have been deposited by a user.

• Return the correct change to a user as coins when an order is received (a
parameter for the value of a product) and remove the coins from the
machine.

Testing
Describe how to run the tests.

Design Decisions
When writing production quality API I would consider using Spring to build a RESTful service.
However for this task a command line program that satisfied the requirements was sufficient.
This was, I could showcase more programming skills and understanding.

The decision to use Big Decimals when calculating the number of coins needed to be dispensed as change
came from the fact floats are not suitable for dividing due to the imprecision.

The instructions provided asked for the initial float to be accepted as a parameter. This implied not individual coins
one at a time. This was ambiguous.

The user is able to deposit coins one at a time, this was handled by writing an enum which contained the accepted coins.

To Run:
javac Main.java
java Main