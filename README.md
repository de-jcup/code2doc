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

A **documentation project** has to be created and documentation elements defined there. It will be referenced by other projects. So every **code inside other projects** can **link to** defined documentation **elements by annotations** of code2doc-api. For example: 
```java 
@LinkToUseCase(UC_1_SHOW_USERS.class)
```
The documentation project itself has only code2doc-api library as dependency.

A **documentation _output_ project** is also created which has documented projects and all code2doc libraries as dependencies. This output project is not part of the real project release but necessary for documentation creation/generation only. Code2Doc libraries provide a tool chain to generate documentation out of the box from the output project.

*Both documentation projects should reside in same SCM repository as other sub projects.*

###Advantages:
- References can be found via IDE (e.g. eclipse)
  - Type hierarchy for UseCase class shows all use cases inside your projects
  - Call reference for your dedicated use case class shows all code parts involved in use case
  - ...
- Specification can be generated
- Documentation is in code
 - versioned by SCM (e.g. git)
 - bind to releases
 - integrateable into build process
- Project specific extensions
  - e.g. ExampleURLExtension for web based projects to decorate their example URLs automatically
- Developers have the documentation inside their code
- Binding to Wiki pages are in code
 - existing documentation can be linked
- Binding to magic code possible...
 - e.g parts belonging to each other but only via Reflection is seen
