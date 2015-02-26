# Java-Commands-Interpreter
This program interprets declarative language, written in java, stores the variables and expressions and executes them.

It reads commands, following a certain format, from a file with a declarative language containing variable declarations and simple arithmetic operations.
The commands read may contain simple declarations or arithmetic operations.

Format of the declarative language in file:

To declare and/or initialize a variable:
Let x = 0;

For arithmetic operations on defined variables/constants:
y = x+1; (this shall add 1 to the value of x and save it in y)
y = 4+3;(this shall add 4 & 3 and save it in y)

Finally the Interpreter should print the variable if the following declaration is encountered:
Print [variable name];

•   Every variable must be initialized at the time of the declaration.
•   Every command must end with a semicolon. Multiple commands can be written in a line.
•   Put a single space after every Keyword, operator and variable. 
•   'Let' keyword is CASE-SENSITIVE shall be used for declaring a new variable and must be written as it is. let/lEt/lET/leT will raise exceptions.


Execution:
Checks for any syntax errors in those commands. Continue executing them if correct. Raises exception otherwise.
e.g. let 0=x or y+x = z are both wrong

Avoid Pitfalls:
•	Any exceptions or errors leading to non-execution
•	Failure to upload the solution to GitHub or not sharing the public repo
•	Failure to handle syntax errors or incorrect declarations(e.g. let 0=x or y+x = z are both wrong)

To Be Added:
Collections in Java and define generic interpretor capable of handling int, float, string double, char & Boolean data types.

