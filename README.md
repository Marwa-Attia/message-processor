# Message Processor
A small message processing application for processing sales notification messages
## Message Processing Functionality
- All sales are recorded
- All valid messages are processed
- An `InvalidMessageException` is thrown for invalid message format  
- After every 10th message received the application will log to the console and store to the project folder a report detailing the number of sales of each product and their total value.
- After 50 messages the application should log that it is pausing, stop accepting new messages and log to the console and store to the project folder a report of the adjustments that have been made to each sale type while the application was running.

## Sales
- A sale has a `productType` String field and a `price` Long field.
- Any number of different product types can be expected.

## Messages
 A message notifying you of a sale could be one of the following types:
 
#### Message Type 1:
`SaleDetailsMessage` - contains the details of 1 sale E.g:

``` sh 
 1,apple,10
```
#### Message Type 2:
`SalesMessage` - contains the details of a sale and the number of occurrences of that sale. E.g:

``` sh 
 2,apple,5,10
```
#### Message Type 3:
`SaleOperationMessage` - contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations can be add, subtract, or multiply e.g:

``` sh 
 3,apple,+,1
```

Supported operators are + for addition, - for subtraction and * for multiplications


## Running the application
- check out the code
- place the message file you want to process in the following path: \src\main\resources
- when prompted by the application enter the file name including the extension and press enter.