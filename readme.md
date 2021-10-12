## For cache problem

For concurrency matters, I’ve added clauses to avoid race conditions. Even pondering  the drawbacks in performance due reentrant-locks.

Did not clone the project before the time’s up. So, I recreated the test from what I could remember. The tests could be different from the Hackerrank problem. But, I considered a 1 million brands under concurrent environment and also a performance test to be sure that I was able to resolve 1 million finds in less than 5 seconds.

My handmade cache version became a bit complicated due not remender what kind of purge strategy was requested. So, I developed LRU, LCU and timeouts.

Since I described that solution in the hacker rank test, I developed the solution I’ve put there during the test. After sleeping over the problem, I realized that I could use a treemap and a linkedhashmap.

So, the final solution would be simple: use  treemap to search (nearest value for a card prefix) and a linkedhashmap to cache (LRU only and not TTL).

Also added this v2 version using treemap and linkedhashmap in the repository.

## For banking problem

For concurrency matters, I’ve added clauses to avoid race conditions. Even pondering  the drawbacks in performance due reentrant-locks.

If the Bank is a Facade, I would like to have a generic way to create the accounts instead of having two methods (openCommercialAccount, openConsumerAccount ). Perhaps with an abstract factory.

Bank class is violating SRP, it looks more like a Facade than anything else;

Transaction also violates SRP due the user validation inside the constructor;

Didn't find the usage for the isAuthorizedUser method in the CommercialAccount. We could replace all those authentication chunks for a specific authentication module verifying permissions before starting any process.

If we extract all the account logic for an external service, we could have anonymization and generalization for the accounts inside the transaction module.

The transaction there has the bank inside itself, that can perhaps make sense in a pure object oriented environment, but is not practical for real life solutions when we need audit logs and repeatable, compensatory events.

Perhaps could be something like my AMQP toy project using messages and event entries to manage the bank account: https://github.com/joseteodoro/amqp-example

I usually do not create interfaces for only one implementation. Since I usually work with evolutionary design guided by the KISS principle, I would leave the interfaces to be extracted when we need to work with abstraction. And yes, I know we should work for abstractions not implementations but we are carrying the complexity before we know if we need more implementations for that interface.

Those items could lead to more readability and simplicity for the project.

For scalability, since I’m using maps to store the data, we cannot scale horizontally. And even for vertical scaling, the hash algorithms have collisions. You know, the law of large numbers.
We could use a database to store data and isolate all the states.

Also for HA we could separate the concerns and isolate the transactions, account info, authentication into separated services. Because the transaction service can be much more used than the account ones. So we can use our computational power better and safer.
