package in.cpl.constants;

import in.cpl.TokenType;

import java.util.HashMap;
import java.util.Map;

public class Codes {

    public static final int INTEGER_CODE = 1000;
    public static final int ID_CODE = 1100;
    public static final int ASSIGNMENT_OPERATOR_CODE = 1101;
    public static final int LE_OPERATOR_CODE = 1102;
    public static final int LT_OPERATOR_CODE = 1103;
    public static final int GE_OPERATOR_CODE = 1104;
    public static final int GT_OPERATOR_CODE = 1105;
    public static final int EQ_OPERATOR_CODE = 1106;
    public static final int NE_OPERATOR_CODE = 1107;
    public static final int ADD_OPERATOR_CODE = 1108;
    public static final int SUB_OPERATOR_CODE = 1109;
    public static final int MUL_OPERATOR_CODE = 1200;
    public static final int DIV_OPERATOR_CODE = 1201;
    public static final int LEFT_PAREN_CODE = 1402;
    public static final int RIGHT_PAREN_CODE = 1403;

    public static final int AND_CODE = 1202;
    public static final int BREAK_CODE = 1203;
    public static final int DO_CODE = 1204;
    public static final int ELSE_CODE = 1205;
    public static final int ELSEIF_CODE = 1206;
    public static final int END_CODE = 1207;
    public static final int FALSE_CODE = 1208;
    public static final int FOR_CODE = 1209;
    public static final int FUNCTION_CODE = 1300;
    public static final int IF_CODE = 1301;
    public static final int IN_CODE = 1302;
    public static final int LOCAL_CODE = 1303;
    public static final int NIL_CODE = 1304;
    public static final int NOT_CODE = 1305;
    public static final int OR_CODE = 1306;
    public static final int REPEAT_CODE = 1307;
    public static final int RETURN_CODE = 1308;
    public static final int THEN_CODE = 1309;
    public static final int TRUE_CODE = 1400;
    public static final int UNTIL_CODE = 1401;
    public static final int WHILE_CODE = 1402;

    public static final int COMMENTS_CODE = 1404;

    public static final int  EOF_CODE = 1202;
   /* public static final int INTEGER_CODE = 1000;
    public static final int FLOAT_CODE = 1001;
    public static final int IDENTIFIER_CODE = 1002;
    public static final int STRING_CODE = 1003;

    public static final int LEFT_PARENTHESIS = 1100;
    public static final int RIGHT_PARENTHESIS = 1101;
    public static final int LEFT_BRACKET = 1102;
    public static final int RIGHT_BRACKET = 1103;
    public static final int EQUAL_SIGN = 1104;
    public static final int ADDITION_OPERATOR = 1105;
    public static final int SUBTRACTION_OPERATOR = 1106;
    public static final int MULTIPLICATION_OPERATOR = 1107;
    public static final int DIVISION_OPERATOR = 1108;
    public static final int EXPONENT_OPERATOR = 1109;
    public static final int LESS_THAN_OPERATOR = 1110;
    public static final int GREATER_THAN_OPERATOR = 1111;
    public static final int COMMA = 1112;

    public static final int SET_CODE = 1200;
    public static final int DEFINE_CODE = 1201;
    public static final int ARRAY_CODE = 1202;
    public static final int FUNCTION = 1203;
    public static final int RETURN = 1204;
    public static final int OF = 1205;
    public static final int TYPE = 1206;
    public static final int PARAMETERS = 1207;
    public static final int IS = 1208;
    public static final int VARIABLES = 1209;
    public static final int BEGIN = 1210;
    public static final int DISPLAY = 1211;
    public static final int WHILE = 1212;
    public static final int DO = 1213;
    public static final int IF = 1214;
    public static final int THEN = 1215;

    public static final int END_IF = 1300;
    public static final int END_WHILE = 1301;
    public static final int END_FUNCTION = 1302;

    public static final int INTEGER_TYPE = 1400;

    public static final int EOF = 9999;
*/
    private Codes() {

    }

    private static Map<TokenType, Integer> codeLookup = new HashMap<>();

    static {
        codeLookup.put(TokenType.INTEGER, INTEGER_CODE);
        codeLookup.put(TokenType.ID, ID_CODE);
        codeLookup.put(TokenType.ASSIGNMENT_OPERATOR, ASSIGNMENT_OPERATOR_CODE);
        codeLookup.put(TokenType.LE_OPERATOR, LE_OPERATOR_CODE);
        codeLookup.put(TokenType.LT_OPERATOR, LT_OPERATOR_CODE);
        codeLookup.put(TokenType.GE_OPERATOR, GE_OPERATOR_CODE);
        codeLookup.put(TokenType.GT_OPERATOR, GT_OPERATOR_CODE);
        codeLookup.put(TokenType.EQ_OPERATOR, EQ_OPERATOR_CODE);
        codeLookup.put(TokenType.NE_OPERATOR, NE_OPERATOR_CODE);
        codeLookup.put(TokenType.ADD_OPERATOR, ADD_OPERATOR_CODE);
        codeLookup.put(TokenType.SUB_OPERATOR, SUB_OPERATOR_CODE);
        codeLookup.put(TokenType.MUL_OPERATOR, MUL_OPERATOR_CODE);
        codeLookup.put(TokenType.DIV_OPERATOR, DIV_OPERATOR_CODE);
        codeLookup.put(TokenType.LEFT_PAREN, LEFT_PAREN_CODE);
        codeLookup.put(TokenType.RIGHT_PAREN, RIGHT_PAREN_CODE);

        codeLookup.put(TokenType.AND, AND_CODE);
        codeLookup.put(TokenType.BREAK, BREAK_CODE);
        codeLookup.put(TokenType.DO, DO_CODE);
        codeLookup.put(TokenType.ELSE, ELSE_CODE);
        codeLookup.put(TokenType.ELSEIF, ELSEIF_CODE);
        codeLookup.put(TokenType.END, END_CODE);
        codeLookup.put(TokenType.FALSE, FALSE_CODE);
        codeLookup.put(TokenType.FOR, FOR_CODE);
        codeLookup.put(TokenType.FUNCTION, FUNCTION_CODE);
        codeLookup.put(TokenType.IF, IF_CODE);
        codeLookup.put(TokenType.IN, IN_CODE);
        codeLookup.put(TokenType.LOCAL, LOCAL_CODE);
        codeLookup.put(TokenType.NIL, NIL_CODE);
        codeLookup.put(TokenType.NOT, NOT_CODE);
        codeLookup.put(TokenType.OR, OR_CODE);
        codeLookup.put(TokenType.REPEAT, REPEAT_CODE);
        codeLookup.put(TokenType.RETURN, RETURN_CODE);
        codeLookup.put(TokenType.THEN, THEN_CODE);
        codeLookup.put(TokenType.TRUE, TRUE_CODE);
        codeLookup.put(TokenType.UNTIL, UNTIL_CODE);
        codeLookup.put(TokenType.WHILE, WHILE_CODE);

        codeLookup.put(TokenType.COMMENTS, COMMENTS_CODE);

        codeLookup.put(TokenType.EOF, EOF_CODE);


        /*codeLookup.put(TokenType.INTEGER, INTEGER_CODE);
        codeLookup.put(TokenType.FLOAT, FLOAT_CODE);
        codeLookup.put(TokenType.IDENTIFIER, IDENTIFIER_CODE);
        codeLookup.put(TokenType.STRING, STRING_CODE);

        codeLookup.put(TokenType.LEFT_PARENTHESIS, LEFT_PARENTHESIS);
        codeLookup.put(TokenType.RIGHT_PARENTHESIS, RIGHT_PARENTHESIS);
        codeLookup.put(TokenType.LEFT_BRACKET, LEFT_BRACKET);
        codeLookup.put(TokenType.RIGHT_BRACKET, RIGHT_BRACKET);
        codeLookup.put(TokenType.EQUAL_SIGN, EQUAL_SIGN);
        codeLookup.put(TokenType.ADDITION_OPERATOR, ADDITION_OPERATOR);
        codeLookup.put(TokenType.SUBTRACTION_OPERATOR, SUBTRACTION_OPERATOR);
        codeLookup.put(TokenType.MULTIPLICATION_OPERATOR, MULTIPLICATION_OPERATOR);
        codeLookup.put(TokenType.DIVISION_OPERATOR, DIVISION_OPERATOR);
        codeLookup.put(TokenType.EXPONENT_OPERATOR, EXPONENT_OPERATOR);
        codeLookup.put(TokenType.LESS_THAN_OPERATOR, LESS_THAN_OPERATOR);
        codeLookup.put(TokenType.GREATER_THAN_OPERATOR, GREATER_THAN_OPERATOR);
        codeLookup.put(TokenType.COMMA, COMMA);

        codeLookup.put(TokenType.SET, SET_CODE);
        codeLookup.put(TokenType.DEFINE, DEFINE_CODE);
        codeLookup.put(TokenType.ARRAY, ARRAY_CODE);
        codeLookup.put(TokenType.FUNCTION, FUNCTION);
        codeLookup.put(TokenType.RETURN, RETURN);
        codeLookup.put(TokenType.OF, OF);
        codeLookup.put(TokenType.TYPE, TYPE);
        codeLookup.put(TokenType.PARAMETERS, PARAMETERS);
        codeLookup.put(TokenType.IS, IS);
        codeLookup.put(TokenType.VARIABLES, VARIABLES);
        codeLookup.put(TokenType.BEGIN, BEGIN);
        codeLookup.put(TokenType.DISPLAY, DISPLAY);
        codeLookup.put(TokenType.WHILE, WHILE);
        codeLookup.put(TokenType.DO, DO);
        codeLookup.put(TokenType.IF, IF);
        codeLookup.put(TokenType.THEN, THEN);

        codeLookup.put(TokenType.END_IF, END_IF);
        codeLookup.put(TokenType.END_WHILE, END_WHILE);
        codeLookup.put(TokenType.END_FUNCTION, END_FUNCTION);

        codeLookup.put(TokenType.INTEGER_TYPE, INTEGER_TYPE);

        codeLookup.put(TokenType.EOF, EOF);
*/
    }

    public static Integer getCodeFromTokenType(final TokenType tokenType) {
        return codeLookup.get(tokenType);
    }
}
