package in.cpl;
import in.cpl.constants.Codes;
//import in.cpl.Scanner;
//import in.cpl.TokenType;
//import in.cpl.Parser;
public class Interpreter {
    public class ArithmeticExpression {

        //private int token_code = TokenType.LITERAL_INTEGER;
        private int token_code = Codes.INTEGER_CODE;
        private String sequence = "0";
        private ArithmeticExpression left = null;
        private ArithmeticExpression right = null;

        public ArithmeticExpression(int code, String seq) {
            this.token_code = code;
            this.sequence = seq;
        }

        public ArithmeticExpression() {
        }

        public void setLeftNode(ArithmeticExpression new_left) {
            this.left = new_left;
        }

        public void setRightNode(ArithmeticExpression new_right) {
            this.right = new_right;
        }

        public void setSequence(String seq) {
            this.sequence = seq;
        }

        public void setTokenCode(int code) {
            this.token_code = code;
        }

        public String getSequence() {
            return this.sequence;
        }

        public int getTokenCode() {
            return this.token_code;
        }

        public ArithmeticExpression getLeftNode() {
            this.left = new ArithmeticExpression();
            return this.left;
        }

        public ArithmeticExpression getRightNode() {
            this.right = new ArithmeticExpression();
            return this.right;
        }

        public static int eval(ArithmeticExpression ae) {
            int value = 0;
            switch (ae.getTokenCode()) {
                case Codes.ID_CODE:
                    value = Integer.parseInt(ae.getSequence());
                    break;
                case Codes.INTEGER_CODE:
                    value = Integer.parseInt(ae.getSequence());
                    break;
                case Codes.ADD_OPERATOR_CODE:
                    value = eval(ae.getLeftNode()) + eval(ae.getRightNode());
                    break;
                case Codes.SUB_OPERATOR_CODE:
                    value = eval(ae.getLeftNode()) - eval(ae.getRightNode());
                    break;
                case Codes.MUL_OPERATOR_CODE:
                    value = eval(ae.getLeftNode()) * eval(ae.getRightNode());
                    break;
                case Codes.DIV_OPERATOR_CODE:
                    value = eval(ae.getLeftNode()) / eval(ae.getRightNode());
                    break;
                default:
                    break;
            }
            return value;
        }

    }


    public class BooleanExpression {

        private ArithmeticExpression left;
        private ArithmeticExpression right;
        private int op;

        public BooleanExpression(ArithmeticExpression l, int op, ArithmeticExpression r) {
            this.left = l;
            this.right = r;
            this.op = op;
        }

        public ArithmeticExpression getLeft() {
            return this.left;
        }

        public ArithmeticExpression getRight() {
            return this.right;
        }

        public int getOperator() {
            return this.op;
        }

        public boolean evaluate() {
            int left_value = ArithmeticExpression.eval(this.left);
            int right_value = ArithmeticExpression.eval(this.right);
            boolean result = false;

            switch (this.getOperator()) {
                case Codes.EQ_OPERATOR_CODE:
                    result = left_value == right_value;
                    break;
                case Codes.LE_OPERATOR_CODE:
                    result = left_value <= right_value;
                    break;
                case Codes.LT_OPERATOR_CODE:
                    result = left_value < right_value;
                    break;
                case Codes.GE_OPERATOR_CODE:
                    result = left_value >= right_value;
                    break;
                case Codes.GT_OPERATOR_CODE:
                    result = left_value > right_value;
                    break;
                default:
                    break;
            }

            return result;

        }

    }
}