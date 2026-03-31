grammar calculator;

equation : postfix EOF #PostFix
         | infix EOF #InFix
         | prefix EOF #PreFix;

// Infix Calculator

infix : add_infix #InBase;

add_infix : mul_infix (sign mul_infix)* #InAdd;

mul_infix : pow_infix (mul pow_infix)* #InMul
          | (atom LPAREN infix RPAREN)+ (atom)? #InMulAtomPrev
          | ((LPAREN infix RPAREN) atom)+ #InMulAtomPost
          ;
pow_infix : factor (pow factor)* #InPow;

factor : fct LPAREN infix (COMMAT infix)* RPAREN    #InFct
       | LPAREN infix RPAREN (LPAREN infix RPAREN)* #InParenthesis
       | (sign)? (num_const)* atom (num_const)*     #InAtom
       ;

// Prefix Calculator

prefix : space_prefix #SpacedPreFix 
       | paren_prefix #ParenthesisPreFix
       ;

space_prefix: (operator | fct)? LPAREN (space_prefix)+ RPAREN #PreSpaceWrappedOp
            | fct space_prefix                                #PreSpaceFct
            | operator space_prefix space_prefix              #PreSpaceSigned
            | LPAREN (sign)? atom RPAREN                      #PreSpaceSigned
            | atom                                            #PreSpaceAtom
            ;

paren_prefix: (operator|fct)? LPAREN paren_prefix (COMMAT paren_prefix)* RPAREN #PreWrappedOp
            | fct paren_prefix                                                  #PreFct
            | LPAREN (sign)? atom RPAREN                                        #PreSigned
            | atom                                                              #PreAtom 
            ;

// Postfix Calculator

postfix : space_postfix #SpacedPostFix 
        | paren_postfix #ParenthesisPostFix
        ;

space_postfix: LPAREN space_postfix (space_postfix)+ RPAREN (operator)? #PostSpaceWrappedOp
             | LPAREN space_postfix (space_postfix)* RPAREN fct         #PostSpaceWrappedFct
             | space_postfix fct                                        #PostSpaceFct
             | space_postfix space_postfix (operator)?                  #PostSpaceSimpleOp
             | LPAREN (sign)? atom RPAREN                               #PostSpaceSigned
             | atom                                                     #PostSpaceAtom
             ;

paren_postfix: LPAREN paren_postfix (COMMAT paren_postfix)+ RPAREN (operator)?  #PostWrappedOp
             | LPAREN paren_postfix (COMMAT paren_postfix)* RPAREN fct          #PostWrappedFct
             | paren_postfix fct                                                #PostFct
             | LPAREN (sign)? atom RPAREN                                       #PostSigned
             | atom                                                             #PostAtom 
             ;



// types of number

atom : number #GlobalNumber
     | complex #ComplexNumber
     ;

// Real numbers 
// Ex: 2, 5.1023 , 10E-8 , 9E12 , pi

number: real #RealNumber
      | scientific #ScientificNumber
      | num_const #Constant
      ;

scientific : INT E (sign)? INT #BaseScientificNumber
;

real : INT (DOT INT)? #BaseNumber ;

// Complex numbers
// Ex : 2i, i 
complex: (number)? I #BaseComplexNumber ; 


// additive operators

sign : PLUS  
     | MINUS
     ; 

// multiplicative operators

mul : TIMES #Times
    | DIV #Div
    ;

// exponantiation operator

pow : POW ;

operator : sign
         | mul 
         | pow
         ;



// numerical constants

num_const: PI #Pi
         | EULER #EulerNumber 
         | PHI #Phi
         ;

I: 'i'; 
E : 'E';
PI :'pi';
EULER: 'e';
PHI: 'phi';

// functions

fct : COS  #FctCos
    | TAN  #FctTan
    | SIN  #FctSin
    | ACOS #FctAcos
    | ATAN #FctAtan
    | ASIN #FctAsin     
    | LN   #FctLn
    | SQRT #FctSqrt
    | LOG  #FctLog
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
