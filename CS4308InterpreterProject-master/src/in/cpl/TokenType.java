package in.cpl;

import java.util.HashMap;

/*LUA has one data type, int, identifiers are single letters
* not case sensitive
* id -> letter
* literal_integer -> digit literal_integer | digit
* assignment_operator -> =
* le_operator -> <=
* lt_operator -> <
* ge_operator -> >=
* gt_operator -> >
* eq_operator -> ==
* ne_operator -> ~=
* add_operator -> +
* sub_operator -> -
* mul_operator -> *
* div_operator -> /*/

public enum TokenType {

    INTEGER(null),

    ID("^[a-zA-Z]*$"),
    //LITERAL_INTEGER(digit literal_integer | digit),
    ASSIGNMENT_OPERATOR("="),
    LE_OPERATOR("<="),
    LT_OPERATOR("<"),
    GE_OPERATOR(">="),
    GT_OPERATOR(">"),
    EQ_OPERATOR("=="),
    NE_OPERATOR("~="),
    ADD_OPERATOR("+"),
    SUB_OPERATOR("-"),
    MUL_OPERATOR("*"),
    DIV_OPERATOR("/"),
    LEFT_PAREN("("),
    RIGHT_PAREN(")"),

    AND("and"),
    BREAK("break"),
    DO("do"),
    ELSE("else"),
    ELSEIF("elseif"),
    END("end"),
    FALSE("false"),
    FOR("for"),
    FUNCTION("function"),
    IF("if"),
    IN("in"),
    LOCAL("local"),
    NIL("nil"),
    NOT("not"),
    OR("or"),
    REPEAT("repeat"),
    RETURN("return"),
    THEN("then"),
    TRUE("true"),
    UNTIL("until"),
    WHILE("while"),

    COMMENTS("--"),

    EOF(null);
    /*INTEGER(null),
    FLOAT(null),
    IDENTIFIER(null),
    STRING(null),

    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    LEFT_BRACKET("["),
    RIGHT_BRACKET("]"),
    EQUAL_SIGN("="),
    ADDITION_OPERATOR("+"),
    SUBTRACTION_OPERATOR("-"),
    MULTIPLICATION_OPERATOR("*"),
    DIVISION_OPERATOR("/"),
    EXPONENT_OPERATOR("^"),
    LESS_THAN_OPERATOR("<"),
    GREATER_THAN_OPERATOR(">"),
    COMMA(","),

    SET("set"),
    DEFINE("define"),
    ARRAY("array"),
    FUNCTION("function"),
    RETURN("return"),
    OF("of"),
    TYPE("type"),
    PARAMETERS("parameters"),
    IS("is"),
    VARIABLES("variables"),
    BEGIN("begin"),
    DISPLAY("display"),
    WHILE("while"),
    DO("do"),
    IF("if"),
    THEN("then"),

    END_IF("endif"),
    END_WHILE("endwhile"),
    END_FUNCTION("endfun"),

    INTEGER_TYPE("integer"),

    EOF(null);
*/
    private static HashMap<String, TokenType> lookUpTable = new HashMap<>();

    static {
        for (TokenType tokenType : values()) {
            if (tokenType.text != null) {
                lookUpTable.put(tokenType.text, tokenType);
            }
        }
    }

    private String text;

    TokenType(final String text) {
        this.text = text;
    }

    public static TokenType getMatchingToken(final String lexeme) {
        return lookUpTable.get(lexeme);
    }

}
