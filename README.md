# code2doc project

## In a nutshell:

The main purpose of this project is to define documentation relevant parts from code itself. So a system documentation can be generated from code and the code itself "knows" the specification.

*Target language is currently Java only.*


## Base concept:

A specification (model) contains of 
- use cases
- architectures
- concepts *( anything leading to a use case - e.g. a story )*
- roles
- constraints

Each of these elements is represented by one java class extending a dedicated abstract element class from code2doc-api.

A **documentation project** is created and referenced by every other sub project. The documentation project has only code2doc-api library as dependency and elements - e.g. use cases - are defined there.

Being common **in all other sub projects** code can use annotations from  code2doc-api and use them to link to the defined elements - e.g. 
```java 
@LinkToUseCase(UC_1_SHOW_USERS.class)
```

A **documentation output project** is additionally created having all documented sub projects and all code2doc libraries as dependencies. This project is not part of the release but only for doumentation creation.

*This project should also be the container of all resource parts used by documentation. So shipped documentation.jar has only annotation relevant classes inside.*

A filled specification is creatable out of the box from the documentation project. The documentation project itself is not part of delivery.

Both documentation projects should reside in same SCM repository as other sub projects.

Advantages:
- References can be found via IDE
  - e.g. Type hierarchy for UseCase class shows all use cases inside your projects
  - e.g. Call reference for your dedicated use case class shows all marked code fragments
- Specification can be generated
- Documentation is in code
 - versioned by SCM (e.g. git)
 - bind to releases
 - integrateable into build process
- Project specific extensions
  - e.g. ExampleURLExtension for web based projects to decorate their example URLs automatically
- Developers have the documentation inside their code
- Binding to Wiki pages are in code

