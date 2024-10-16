# scratch
What would a web-app for tracking storyboard interactions between characters look like? Also an excuse for trying out software engineering things.

## Technical Agenda
### Anemic Domain Models
One of the most curious practices that I keep seeing consistently is the use of [anemic domain models](https://martinfowler.com/bliki/AnemicDomainModel.html).
Almost all the project I've worked with tend to use this where their "domain data" are merely POJOs and all the actual domain logic reside in a service elsewhere.
I'm not here to claim whether it's bad or there's a better way. However, I am here to give domain models a proper shot -- at least based on how I understand it.
In this project, I'll strive to keep the use of services to just providing value-add utilities such as interacting with the database and external resources.
Any logic and rules in actually performing the core tasks will be done by what I think domain models should be. Can I do that given the tools that I would choose to use? I don't know.

### Reactive Streams
In Chapter 5 of my personal journal in Re-learning to Code, I've played around with Reactive Streams a bit but not really enough to say that I've "used" it.
I'll be fully implementing this small project using Spring Webflux and see how it would affect any implementation that I would otherwise treat as typical.

### NoSQL Document Databases
In relation to the topic of not using Anemic Domain Models, in my time trying to play around with noSQL databases, I _think_ that there's a good link between noSQL and non-anemic domain models.
I'm not sure how whether it will naturally work but if my understanding of how domain models should be structured is true, then I can decouple persistence with the model.
This means that the persistence layer can take in a complete domain model and encapsulate how it translates that model into a persistence-compatible structure.
I don't plan to delve into it too much but, I plan to approach this naively by treating the entire domain model as one big document that I frequently persist.
Whether I would need to evolve this approach and be able to keep it separate from the domain model is something I'd like to uncover.


