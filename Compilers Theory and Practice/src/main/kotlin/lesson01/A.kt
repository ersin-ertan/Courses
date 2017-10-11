package lesson01

/*
 Compiler: translates source language to target language

 source > compiler > executable > processor (compiled faster)
 source > interpreter > displays program (slower)

Why: high level lang, to make changes quickly which is compiled to assembly which is assembled to machine language

Front End: Scanner, parser, semantic action
Highly customized to the language-dependent

Scanner reads like a book and sends words(tokens) built by characters to the parser
Scanner sees 123.3123 and produces FLOATCONST with value of 123.3123, value and FLOATCONST is used by parser.

Parsing the sentence is to check that the syntax matches existing rules, and that the 'Semantic action' of checking
types is valid(semantic error on int plus string). Then semantic action translates sentence to Intermediate Representation.


Starting the compiler we start the Parser, which track where we are during compilation, and how much of the sentence we
are using. Parse, get next token from source, give token to parser(determine syntax/semantic), loop. Then we have partial
sentence for semantic analysis, which looks up variable name in the Symbol table(holds name value, scope, type), then
does semantic checks to see if type operations are valid. If it's a declaration, put it into the symbol table. Then when
it is valid it will generate code, as the IR. Or semantic error.

Compiler:
- Front End Analysis
    - Lexical Analysis
    - Syntax analysis
    - Semantic analysis
- Back End Analysis
    - Code Generation
    - Optimization



The big picture:
Scanning: converts input text to stream of known tokens, simplifies parsing process
Parsing: Translating code to rules of grammar, building representation of code. Controls activity with partial sentences,
demands scanner tokens

The lexical rules of teh language dictate how a legal word is formed by concatenating the alphabet
Grammar dictates syntactic rules of language

Scanning and tokenization will scanner each char so long as it forms a legal token, a char too many will stop scanning
and return the token to the parser. Can use longest match algorithm.

Parsing applys the rules of grammar to see if the phrase is correct.

Semantic check can be expensive if it is context sensitive.

Grammars should be designed to eliminate ambiguity by explicitly stating structure and control flow. ex.
2 * 2 + 3, or if a then if b then c else d

Associate else with inner most if.

Symbol table - name:type:scope used in semantic analysis
Enter variable declaration into symbol table -> look up variables in table, binding analysis(scoping rules and block
struct) to bind the variable to the inner most scope -> use it's type value for type compatibility check -> keep the
semantic context of processing(the subexpression which has it's own type)

Action symbols embedded in the grammar, called by parser - represents a semantic procedure, do work or return values.
Semantic analysis uses a semantic stack, which stores semantic records
Ex. semantic stack
type->id1->id2->id3
so when you do-decl it pushes the linked group's info to the symbol table

Semantic actions:
- checking: bindinh, type compatibility, scoping
- translation: generate temporary values, propagate them to keep semantic context


*/
