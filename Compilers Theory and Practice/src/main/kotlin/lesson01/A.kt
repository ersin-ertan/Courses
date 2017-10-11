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



*/
