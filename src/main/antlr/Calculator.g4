grammar Calculator;


// Infix Calculator

infix : add_infix;

add_infix : mul_infix (sign mul_infix)*;
mul_infix : pow_infix (mul pow_infix)*;
pow_infix : factor (pow factor)*;

factor : fct LPAREN infix RPAREN 
       | LPAREN infix RPAREN (LPAREN infix RPAREN)*
       | LOG LPAREN infix COMMAT infix RPAREN 
       | (sign)? atom ;

// types of number

atom : number | complex ;

// Real numbers 
// Ex: 2, 5.1023 , 10E-8 , 9E12 , pi

number: INT ((DOT INT) | (E (sign)? INT))? | num_const ;

// Complex numbers
// Ex : 2i, i 
complex: (number)? I;


// additive operators

sign : PLUS | MINUS ;

// multiplicative operators

mul : TIMES | DIV ;

// exponantiation operator

pow : POW;

// numerical constants

num_const: PI | EULER | PHI;

I: "i"; 
E : "E";
PI :"pi";
EULER: "e";
PHI: "phi";

// functions

fct : COS
    | TAN
    | SIN
    | ACOS
    | ATAN
    | ASIN
    | LN
    | SQRT
    ;

COS : "cos";
SIN : "sin";
TAN : "tan";
ACOS : "acos" ;
ASIN : "asin" ;
ATAN : "atan" ;
LN : "ln" ;
LOG : "log" ;
SQRT : "sqrt" ;



PLUS : "+";
MINUS : "-";
TIMES : "*";
DIV : "/";
POW : "**";


fragment INT : ("0" .."9")+;
fragment LPAREN : "(" ;
fragment RPAREN : ")" ;
fragment DOT : ".";
fragment COMMAT: ",";

