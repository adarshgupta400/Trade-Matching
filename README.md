
<h1 align="center">
Trade-Matching
</h1>

# Description:

- Implementation of Trade Matching and Confirmations logic after trade Execution. In Post-trade world , we need strong automatic matching system so that party's version of executed trade can be matched accurately with counterParty trade details.
- The client should be able to search for a trade by entering the Party Name and Trade Reference Number, and by Party Name and Trade Status (Fast Searching).
- The client should also be able to get the list of all cancelled trades which have combined to form an aggregated trade by providing the trade Id of the aggregated trade.
- CRUD for Institution,Party and Trades are implemented
- All Validation on Trades,error handling, Custom Responses, unit test cases and end to end testing covered

<img src="https://user-images.githubusercontent.com/59231700/192249469-aaf082c1-451e-48d2-8676-128e1b2ecabd.png">
<img src="[https://user-images.githubusercontent.com/53696144/190675588-ac92183e-76f3-4506-a403-1d1d5876c2ae.png](https://user-images.githubusercontent.com/59231700/192250437-7db20735-c820-4ddb-8531-eff80346cc55.png)" width="500px" height="400px">
<img src="[https://user-images.githubusercontent.com/53696144/190675588-ac92183e-76f3-4506-a403-1d1d5876c2ae.png]([https://user-images.githubusercontent.com/59231700/192250437-7db20735-c820-4ddb-8531-eff80346cc55.png](https://user-images.githubusercontent.com/59231700/192250733-f910e978-3133-4b24-b489-bd6ae3c29df7.png))" width="500px" height="400px">


# 🚀 Technical Stack:

- MySQL (DB)
- Spring Boot and JPA, Hibernate
- Junit, Mockito and Rest Assured

### Screenshots

<img src="https://user-images.githubusercontent.com/53696144/190675155-59fdebc2-1c8f-44d0-8c66-8b7ac32361e0.png" width="400px" align="right"  >

<img src="https://user-images.githubusercontent.com/53696144/190676006-52e9f05c-b355-49fc-a029-d88044c063f8.png" width="400px">

<img src="https://user-images.githubusercontent.com/53696144/190676236-79bc4f4c-25a7-4567-99ad-dc7baea9c3c9.png" width="600px" align="right">

<img src="https://user-images.githubusercontent.com/53696144/190677167-b7c04ac0-b7c8-49f3-b65b-f7fa76ef5591.png" width="300px"  >

<img src="https://user-images.githubusercontent.com/53696144/190677893-0b10fff8-e256-4478-adc0-f2bff8331982.png" width="600px" height="100px" align="right">

<img src="https://user-images.githubusercontent.com/53696144/190678304-33cb45b6-0e3a-4cd2-ab93-ac1611cae951.png" width="400px" height="300px" >

## Here are my social media handles:

Linkedin: https://github.com/adarshgupta400

###### Email: adarshgupta400@gmail.com

### Thank you!























###Problem Statement
When a trade is submitted by a party, the system will run a matching logic against all unconfirmed trades in system and find a best match (based on certain trade parameters match) with counterparty trade. 
If a 100% match found, then Trade and respected counterparty match trade will move to Confirmed State. 
If few trade found with < 100% matching score, then we need to create a link of trade & counterparty trade with their respective matching score.
What needs to be done

###Build a Swagger Based REST API which will deliver below functionality

###API 1 -> Create a new trade, Trade Data will be consider as Input parameter (with version 0)
	Ensure that Party and Counterparty must be a valid party 
	When trade is inputted, perform validations and if not valid reject the trade
	If trade is valid save the trade and generate response with Trade Successfully created
	Post the persistence, run a matching logic to find a matching trade in system and capture the matching score. (persist information in system to retrieve the trade details)
	
###API 2 -> Search for a trade
	By Party & Trade Reference Number => List the specific trade and matching score / counterparty & counterparty trade reference number 
	By Party & Trade Status => List all trade for respective party with the provided status
	
###API 3 -> Update an existing Trade (must be the reflecting the current version)
	Can be Updated with trade details, Cancelled. Exit will be trigged on Trade Maturity Date
 
###Rules -> 
** A static mapping file exists which contains set of valid Institution ID and its associated Party ID.
Party ID & Counterparty ID are Unique across the mapping file. Which means Party ID can be associated to only one Institution
An institution can have multiple party ids.
Enrich the Institution ID when a new trade is submitted or existing trade modified.
