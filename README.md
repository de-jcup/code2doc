# code2doc project

## In a nutshell:

The main purpose of this project is to define documentation relevant parts from code itself. So documentation can be generated from code. Target language is currently Java only.


## Base concept:

A specification (model) contains of 
- use cases
- architectures
- concepts
- roles
- constraints

elements.

Each of these element is represented by a dedicated class extending an abstract element class.

A **documentation project** is created and used as common project by every other sub project. The documentation project does use code2doc-api as dependency and elements - e.g. use cases - are defined there.

Being common all other project code can use annotations inside code2doc-api and use them to link to the defined elements

A **documentation output project** is additionally created having all other projects as dependencies *(so in class path avilable)* and dedicated all necessary code2doc-parts *(e.g. code2doc-core, code2doc-generator, code2doc-renderer-docbook)*

A filled specification is creatable out of the box from the documentation project. The documentation project itself is not part of delivery.

Advantages:
- References can be found via IDE
- Specification can be created automatical.
- Developers have the documentation inside their code
- Binding to Wikis are in code

