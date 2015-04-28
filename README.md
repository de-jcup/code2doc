# code2doc project

## In a nutshell:

The main purpose of this project is to **link or define documentation with code** - this is done by **code2doc-api**.
(*Target language is currently Java only.*)

With code2doc **also a system documentation can be generated from code itself**. This requires code2doc-api and other code2doc parts.

## Base concept:

A specification (model) contains of 
- use cases
- architectures
- concepts *( anything leading to a use case - e.g. a story )*
- roles
- constraints


### Link or define documentation with code
A **documentation project** must be created. All documentation elements are defined there and is referenced by every other project. So **code inside other projects** can **link to** defined documentation **elements by annotations** of code2doc-api. For example: 
```java 
@LinkToUseCase(UC_1_SHOW_USERS.class)
```

### Generate a system documentation
When a system documentation shall be generated an additionally **documentation _output_ project** has to be created which has documented projects and other code2doc libraries as dependencies. This output project is not part of the real project release but necessary for documentation creation/generation only. Code2Doc libraries provide a tool chain to generate documentation out of the box from the output project.

## Demo showcase
There is a demo showcase available, containing of three projects: 
- https://github.com/de-jcup/code2doc/tree/master/code2doc-demo-documentation
- https://github.com/de-jcup/code2doc/tree/master/code2doc-demo
- https://github.com/de-jcup/code2doc/tree/master/code2doc-demo-documentation-output


## Advantages:
- References can be found via IDE (e.g. eclipse)
  - Type hierarchy 
    - e.g. find all existing use cases inside your projects
  - Call references 
    - e.g. find all involved code parts for a dedicated use case
  
- Documentation
 - link from code to WIKI 
 - and/or define documentation in code itself
    - versioned by SCM (e.g. git)
    - bound to releases
 - generateable
   - out of the box 
   - integrateable into build process
 - extendable
   - e.g. ExampleURLExtension for web based projects to decorate their example URLs automatically

- Sustainable
 - code2doc-api is simple plain java...
