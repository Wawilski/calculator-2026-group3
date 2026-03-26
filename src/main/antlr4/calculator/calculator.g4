grammar calculator;

equation : ( postfix | infix | prefix)  ;

// Infix Calculator

infix : add_infix;

add_infix : mul_infix (sign mul_infix)*;

mul_infix : pow_infix (mul pow_infix)*
          | (LPAREN infix RPAREN)* atom (LPAREN infix RPAREN)*
;
pow_infix : factor (pow factor)*;

factor : fct LPAREN infix (COMMAT infix)* RPAREN 
       | LPAREN infix RPAREN (LPAREN infix RPAREN)*
       | (sign)? atom (num_const)*;

// Prefix Calculator

prefix : space_prefix | paren_prefix;

space_prefix: (operator | fct)? LPAREN (space_prefix)+ RPAREN
            | fct space_prefix
            | operator space_prefix space_prefix
            | LPAREN sign atom RPAREN
            | atom;

paren_prefix: (operator|fct)? LPAREN paren_prefix (COMMAT paren_prefix)* RPAREN
            | fct paren_prefix
            | LPAREN sign atom RPAREN
            | atom;


// Postfix Calculator

postfix : space_postfix | paren_postfix;

space_postfix: LPAREN (space_postfix)+ RPAREN (operator | fct )? 
            | space_postfix fct
            | space_postfix space_postfix operator 
            | LPAREN (sign)? atom RPAREN
            | atom;

paren_postfix: LPAREN paren_postfix (COMMAT paren_postfix)* RPAREN (operator | fct)?
             | paren_postfix fct
            | LPAREN (sign)? atom RPAREN
            | atom;



// types of number

atom : number | complex ;

// Real numbers 
// Ex: 2, 5.1023 , 10E-8 , 9E12 , pi

number: real | scientific | num_const ;

scientific : INT E (sign)? INT ;

real : INT (DOT INT)? ;

// Complex numbers
// Ex : 2i, i 
complex: (number)? I;


// additive operators

sign : PLUS | MINUS ;

// multiplicative operators

mul : TIMES | DIV ;

// exponantiation operator

pow : POW;

operator : sign | mul | pow;



// numerical constants

num_const: PI | EULER | PHI;

I: 'i'; 
E : 'E';
PI :'pi';
EULER: 'e';
PHI: 'phi';

// functions

fct : COS
    | TAN
    | SIN
    | ACOS
    | ATAN
    | ASIN           
    | LN
    | SQRT
    | LOG
    ;
           
COS : 'cos';
SIN : 'sin';
TAN : 'tan';
ACOS : 'acos' ;
ASIN : 'asin' ;
ATAN : 'atan' ;
LN : 'ln' ;
LOG : 'log' ;
SQRT : 'sqrt' ;



PLUS : '+';
MINUS : '-';
TIMES : '*';
DIV : '/';
POW : '**';


INT : ('0' ..'9')+;
LPAREN : '(' ;
RPAREN : ')' ;
DOT : '.';
COMMAT: ',';

WS
    : [ \r\n\t]+ -> skip
    ;
