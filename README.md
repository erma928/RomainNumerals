### INSRUCTIONS TO RUN

1. `unzip galaxy.zip`
2. `cd galaxy/`
2. `ant`
3. `java -cp galaxy.jar com.galaxy.GalaxyApp <path to input file>`


### PROGRAM DESIGN

1. class design

        |- Symbol
            |- GalaxySymbol
            |- RomanSymbol
        |- Number
            |- GalaxyNumber
            |- RomanNumber
        |- NumberBuilder
            |- GalaxyNumberBuilder
            |- RomanNumberBuilder
        |- NumberParser
            |- BaseNumberParser
        |- ParseRule
            |- BaseDisjunctiveRule
                |- DescendingRule
                |- RepeatableRule
                |- SubtractableRule
        |- NumberBuilderFactory
        |- GalaxyNotesParser
        |- MetalPriceCalculator
    		
	* `Symbol` classes: symbols with the number systems; also encodes the rules for conversion btw them
	* `Number` classes: immutable classes for the numbers, no direct instantiation is allowed
	* `NumberParser` classes: generic class to encode the algorithm for parsing the numbers
	* `NumberBuilder` classes: generic class to build the numbers; numbers are only allowed to be created via builder
	* `ParserRule` classes: encodes the parsing rules; the rules can be disjunctive or conjunctive
	* `NumberBuilderFactory` class: factory class to create builders
	* `GalaxyNotesParser` class: I/O supported
2. algorithm for parsing roman numerals:
    * O(n) complexity; makes use of stack for storing intermediate results in processing loop
3. rationale behind:
    * By restricting the creation of numbers to builders, we can encapsulate rules properly for the number system
    * By restricting the creation of builders to `NumberBuilderFactory`, we can control the proper initialization of them
    * By using generic `NumberParser` classes, we are able to abstract common parsing logic for different numerals
    * By using `ParseRule` classes, we can use different set of rules for different numeral systems, making it easy to extend
    
3. more:
	* please refer to the code and documentations.
