import scanner.Tokenizer;

public class ArithmeticExpression {
    
    private int token_code = Tokenizer.LITERAL_INTEGER_N;
    private String sequence = "0";
    private ArithmeticExpression left = null;
    private ArithmeticExpression right = null;
    
    public ArithmeticExpression(int code, String seq){
        this.token_code = code;
        this.sequence = seq;
    }
    
    public ArithmeticExpression(){}
    
    public void setLeftNode(ArithmeticExpression new_left){
        this.left = new_left;
    }
    
    public void setRightNode(ArithmeticExpression new_right){
        this.right = new_right;
    }
    
    public void setSequence(String seq){
        this.sequence = seq;
    }
    
    public void setTokenCode(int code){
        this.token_code = code;
    }
    
    public String getSequence(){
        return this.sequence;
    }
    
    public int getTokenCode(){
        return this.token_code;
    }
    
    public ArithmeticExpression getLeftNode(){
        this.left = new ArithmeticExpression();
        return this.left;
    }
    
    public ArithmeticExpression getRightNode(){
        this.right = new ArithmeticExpression();
        return this.right;
    }
    
    public static int eval(ArithmeticExpression ae){
        int value = 0;
        switch (ae.getTokenCode()) {
            case Tokenizer.IDENTIFIER_N:
                value = Integer.parseInt(ae.getSequence());
                break;
            case Tokenizer.LITERAL_INTEGER_N:
                value = Integer.parseInt(ae.getSequence());
                break;
            case Tokenizer.ADD_OPERATOR_N:
                value = eval(ae.getLeftNode()) + eval (ae.getRightNode());
                break;
            case Tokenizer.SUB_OPERATOR_N:
                value = eval(ae.getLeftNode()) - eval(ae.getRightNode());
                break;
            case Tokenizer.MUL_OPERATOR_N:
                value = eval(ae.getLeftNode()) * eval(ae.getRightNode());
                break;
            case Tokenizer.DIV_OPERATOR_N:
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
    
    public BooleanExpression(ArithmeticExpression l, int op, ArithmeticExpression r){
        this.left = l;
        this.right = r;
        this.op = op;
    }
    
    public ArithmeticExpression getLeft(){
        return this.left;
    }
    
    public ArithmeticExpression getRight(){
        return this.right;
    }
    
    public int getOperator(){
        return this.op;
    }
    
    public boolean evaluate(){
        int left_value = ArithmeticExpression.eval(this.left);
        int right_value = ArithmeticExpression.eval(this.right);
        boolean result = false;
        
        switch (this.getOperator()) {
            case Tokenizer.EQ_OPERATOR_N:
                result = left_value == right_value;
                break;
            case Tokenizer.GE_OPERATOR_N:
                result = left_value <= right_value;
                break;
            case Tokenizer.GT_OPERATOR_N:
                result = left_value < right_value;
                break;
            case Tokenizer.LE_OPERATOR_N:
                result = left_value >= right_value;
                break;
            case Tokenizer.LT_OPERATOR_N:
                result = left_value > right_value;
                break;
            case Tokenizer.NE_OPERATOR_N:
                result = left_value == right_value; //We have integers and it is the only valid representations
                break;
            default:
                break;
        }
        
        return result;
        
    }

}
