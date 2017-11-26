package in.cpl;

import java.util.LinkedList;
import java.util.List;
import jdk.nashorn.internal.parser.Token;
import jdk.nashorn.internal.runtime.ParserException;

import Token;
import TokenType;
import ParsableToken;
import Scanner;

public class Parser{

	 LinkedList<Token> tokens;
	 Token lookahead;
	  
	 public void parse(List<Token> tokens)
	  {
	    this.tokens = (LinkedList<Token>) tokens.clone();
	    lookahead = this.tokens.getFirst();

	    expression();
	  }

	 private void nextToken()
	  {
		 if(!tokens.isEmpty()){
			 System.out.println("Valid: " + lookahead);
			 tokens.pop();
			 lookahead = tokens.getFirst();
		 }
		 else{
			 System.out.println("Token list empty");
		 }
	  }
	 
	 private void expression()
	  {
		 while(!tokens.isEmpty()){
			 if (lookahead.token == Token.ID){
				 assign();
			 }
			 else if (lookahead.token == Token.FUNCTION){
				 function();
			 }
			 else if (lookahead.token == Token.WHILE){
				 whileLoop();
			 }
			 else if (lookahead.token == Token.IF){
				 ifLoop();
			 }
			 else if (lookahead.token == Token.REPEAT){
				 repeat();
			 }
			 else if (lookahead.token == Token.PRINT){
				 print();
			 }
		 }
	  }
	 
	 private void assign()
	  {
		//<assignment_statement> -> id <assignment_operator> <arithmetic_expression>
		 nextToken();
		 if(!lookahead.token == Token.ASSIGNMENT_OPERATOR){
			 throw new ParserException("Assignment operator expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 arithmetic(); 
	  }
	 
	 private void function()
	  {
		 //<program> → function id ( ) <block> end
		 nextToken();
		 if(!lookahead.token == Token.ID){
		 throw new ParserException("ID expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 if(!lookahead.token == Token.LEFT_PAREN){
		 throw new ParserException("Left parenthesis expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 if(!lookahead.token == Token.RIGHT_PAREN){
		 throw new ParserException("Right parenthesis expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 while(!lookahead.token == Token.END){
			 statement();
		 }
		 nextToken();
		}
	 
	 private void whileLoop()
	  {
		 //while <boolean_expression> do <block> end
		 nextToken();
		 booleanFun();
		 if(!lookahead.token == Token.DO){
		 throw new ParserException("Do expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 while(!lookahead.token == Token.END){
			 statement();
		 }
		 nextToken();
	  }
	 
	 private void ifLoop()
	  {
		 //if <boolean_expression> then <block> else <block> end
		 nextToken();
		 booleanFun();
		 if(!lookahead.token == Token.THEN){
		 throw new ParserException("Then expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 while(!lookahead.token == Token.ELSE){
			 statement();
		 }
		 nextToken();
		 while(!lookahead.token == Token.END){
			 statement();
		 }
		 nextToken();
	  }
	 
	 private void repeat()
	  {
		 //<repeat_statement> -> repeat <block> until <boolean_expression>
		 nextToken();
		 while(!lookahead.token == Token.UNTIL){
			 statement();
		 }
		 nextToken();
		 booleanFun();
	  }
	 
	 private void print()
	  {
		 //<print_statement> → print ( <arithmetic_expression> )
		 nextToken();
		 if(!lookahead.token == Token.LEFT_PAREN){
			  throw new ParserException("Left parenthesis expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken(); 
		 }
		 arithmetic();
		 if(!lookahead.token == Token.RIGHT_PAREN){
			  throw new ParserException("Right parenthesis expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken(); 
		 }
	  }
	 
	 private void booleanFun()
	  {
		 //<boolean_expression> → <relative_op> <arithmetic_expression> <arithmetic_expression>
		 relativeOp();
		 arithmetic();
		 arithmetic();
	  }
	 
	 private void relativeOp()
	 {
	 	if(lookahead.token == Token.LE_OPERATOR || lookahead.token == Token.LT_OPERATOR || lookahead.token == Token.GE_OPERATOR
	 	|| lookahead.token == Token.GT_OPERATOR || lookahead.token == Token.EQ_OPERATOR || lookahead.token == Token.NE_OPERATOR){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Relative operator expected and "
			          + lookahead.sequence + " found instead");
		 }
	 }
	 
	 private void arithmetic()
	  {
		 /*<arithmetic_expression> → <id> | <literal_integer> |
		  *  <arithmetic_op> <arithmetic_expression> <arithmetic_expression>
		  */
		 if(lookahead.token == Token.ID || lookahead.token == Token.LITERAL_INTEGER){
			 nextToken(); 
		 }
		 else if(lookahead.token == Token.ADD_OPERATOR || lookahead.token == Token.SUB_OPERATOR
				 || lookahead.token == Token.MUL_OPERATOR || lookahead.token == Token.DIV_OPERATOR){
			 arithmetic();
			 arithmetic();
		 }
		 else{
			 throw new ParserException("ID, integer, or arithmetic operator expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void statement()
	 {
		 if (lookahead.token == Token.ID){
			 assign();
		 }
		 else if (lookahead.token == Token.WHILE){
			 whileLoop();
		 }
		 else if (lookahead.token == Token.IF){
			 ifLoop();
		 }
		 else if (lookahead.token == Token.REPEAT){
			 repeat();
		 }
		 else if (lookahead.token == Token.PRINT){
			 print();
		 }
	 }
	 
}