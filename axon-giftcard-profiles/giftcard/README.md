# Giftcard demo - single module

The application needs Axon Server. See https://axoniq.io/download for various options of
obtaining Axon Server 
(Docker image, jar download).

you can run this application as a monolith.
system. The following 3 profiles are available:
* `command`: Contains the Giftcard aggregate
* `query`: Contains the Giftcard read model
* `client`: Executes a few test commands and a test query

By passing these as JVM arguments `spring.profiles.active` to `command,query,client`, you'll run the app
as a monolith.
