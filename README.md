## Trade-Matching
#Problem Statement
When a trade is submitted by a party, the system will run a matching logic against all unconfirmed trades in system and find a best match (based on certain trade parameters match) with counterparty trade. 
If a 100% match found, then Trade and respected counterparty match trade will move to Confirmed State. 
If few trade found with < 100% matching score, then we need to create a link of trade & counterparty trade with their respective matching score.
What needs to be done
#Build a Swagger Based REST API which will deliver below functionality
API 1 -> Create a new trade, Trade Data will be consider as Input parameter (with version 0)
	Ensure that Party and Counterparty must be a valid party 
	When trade is inputted, perform validations and if not valid reject the trade
	If trade is valid save the trade and generate response with Trade Successfully created
	Post the persistence, run a matching logic to find a matching trade in system and capture the matching score. (persist information in system to retrieve the trade details)
#API 2 -> Search for a trade
	By Party & Trade Reference Number => List the specific trade and matching score / counterparty & counterparty trade reference number 
	By Party & Trade Status => List all trade for respective party with the provided status
#API 3 -> Update an existing Trade (must be the reflecting the current version)
	Can be Updated with trade details, Cancelled. Exit will be trigged on Trade Maturity Date
 
#Rules -> 
** A static mapping file exists which contains set of valid Institution ID and its associated Party ID.
Party ID & Counterparty ID are Unique across the mapping file. Which means Party ID can be associated to only one Institution
An institution can have multiple party ids.
Enrich the Institution ID when a new trade is submitted or existing trade modified.
