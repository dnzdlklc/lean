Lean Coding Exercise

Although authentication is carried out by Spring Security, as there is no valid JWT nor users to check against the
mechanism merely checks that a supplied "lean-token" is valid if so user is authorised and a dummy users is pushed
onto context as authorised. In a real world JWT would be parsed and checked against DB records.

Aside from above this is code strictly follows outlined guides on the instructionand nothing more. Application could be dockerised
and some test could be adjusted to run against real "REPOSITORY" classes if need be although in unit testing context this might be an overkill.


To run code see below:

1. Build the code base `mvn clean compile install`
2. Run via Maven using Boot: `mvn spring-boot:run`

Once running API can be consumed:

`localhost:8080/customer/1` and so on...

All test will be executed as part of build but can also be ran independently if need be.

