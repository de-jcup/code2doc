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

### Inside approach
A common project uses code2doc-api as dependency and elements - e.g. use cases - are defined there.
Being common all other project code can use annotations inside code2doc-api and use them to link to the defined elements

A documentation project is additional created having all other projects as dependencies (so in class path avilable) and dedicated code2doc-parts (e.g. code2doc-core, code2doc-generator, code2doc-renderer-docbook)
Via an annotation collector a filled specification is creatable out of the box from the documentation project. The documentation project itself is not part of delivery.

Advantage:
- Specification can be created completeley automatical.
- References can be found

Disadvantage:
- Runtime dependency in project to code2doc-api (but normally no problem because Apache 2.0 licence...)

### Outside approach
Only a documentation project is additional created having all other projects as dependencies (so in class path avilable) and dedicated code2doc-parts (e.g. code2doc-core, code2doc-generator, code2doc-renderer-docbook)
There must be a empty specification created and filled manually (java dsl style).


Advantage:
- no dependency from project to code2doc

Disadvantage:
- ugly to use
