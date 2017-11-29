package in.cpl;

import java.util.LinkedList;
import java.util.List;
import jdk.nashorn.internal.runtime.ParserException;


//import in.cpl.Token.*;
//import in.cpl.TokenType.*;
//import in.cpl.ParsableToken.*;
//import in.cpl.Scanner.*;

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
			 if (lookahead.getTokenType() == TokenType.ID){
				 assign();
			 }
			 else if (lookahead.getTokenType() == TokenType.FUNCTION){
				 function();
			 }
			 else if (lookahead.getTokenType() == TokenType.WHILE){
				 whileLoop();
			 }
			 else if (lookahead.getTokenType() == TokenType.IF){
				 ifLoop();
			 }
			 else if (lookahead.getTokenType() == TokenType.REPEAT){
				 repeat();
			 }
			 /*else if (lookahead.token == TokenType.PRINT){
				 printLoop();
			 }*/
		 }
	  }
	 
	 private void assign()
	  {
		//<assignment_statement> -> id <assignment_operator> <arithmetic_expression>
		 nextToken();
		 if(lookahead.getTokenType() != TokenType.ASSIGNMENT_OPERATOR){
			 throw new ParserException("Assignment operator expected and "
			          + lookahead.getLexeme() + " found instead");
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
		 if(lookahead.getTokenType() != TokenType.ID){
		 throw new ParserException("ID expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 if(lookahead.getTokenType() != TokenType.LEFT_PAREN){
		 throw new ParserException("Left parenthesis expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 if(lookahead.getTokenType() != TokenType.RIGHT_PAREN){
		 throw new ParserException("Right parenthesis expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 } 
		 while(lookahead.getTokenType() != TokenType.END){
			 statement();
		 }
		 nextToken();
		}
	 
	 private void whileLoop()
	  {
		 //while <boolean_expression> do <block> end
		 nextToken();
		 booleanFun();
		 if(lookahead.getTokenType() != TokenType.DO){
		 throw new ParserException("Do expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 while(lookahead.getTokenType() == TokenType.END){
			 statement();
		 }
		 nextToken();
	  }
	 
	 private void ifLoop()
	  {
		 //if <boolean_expression> then <block> else <block> end
		 nextToken();
		 booleanFun();
		 if(lookahead.getTokenType() != TokenType.THEN){
		 throw new ParserException("Then expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 while(lookahead.getTokenType() != TokenType.ELSE){
			 statement();
		 }
		 nextToken();
		 while(lookahead.getTokenType() != TokenType.END){
			 statement();
		 }
		 nextToken();
	  }
	 
	 private void repeat()
	  {
		 //<repeat_statement> -> repeat <block> until <boolean_expression>
		 nextToken();
		 while(lookahead.getTokenType() != TokenType.UNTIL){
			 statement();
		 }
		 nextToken();
		 booleanFun();
	  }
	 
	 private void printLoop()
	  {
		 //<print_statement> → print ( <arithmetic_expression> )
		 nextToken();
		 if(lookahead.getTokenType() != TokenType.LEFT_PAREN){
			  throw new ParserException("Left parenthesis expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 arithmetic();
		 if(lookahead.getTokenType() != TokenType.RIGHT_PAREN){
			  throw new ParserException("Right parenthesis expected and "
			          + lookahead.getLexeme() + " found instead");
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
	 	if(lookahead.getTokenType() == TokenType.LE_OPERATOR || lookahead.getTokenType() == TokenType.LT_OPERATOR || lookahead.getTokenType() == TokenType.GE_OPERATOR
	 	|| lookahead.getTokenType() == TokenType.GT_OPERATOR || lookahead.getTokenType() == TokenType.EQ_OPERATOR || lookahead.getTokenType() == TokenType.NE_OPERATOR){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Relative operator expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
	 }
	 
	 private void arithmetic()
	  {
		 /*<arithmetic_expression> → <id> | <literal_integer> |
		  *  <arithmetic_op> <arithmetic_expression> <arithmetic_expression>
		  */
		 if(lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.INTEGER){
			 nextToken(); 
		 }
		 else if(lookahead.getTokenType() == TokenType.ADD_OPERATOR || lookahead.getTokenType() == TokenType.SUB_OPERATOR
				 || lookahead.getTokenType() == TokenType.MUL_OPERATOR || lookahead.getTokenType() == TokenType.DIV_OPERATOR){
			 arithmetic();
			 arithmetic();
		 }
		 else{
			 throw new ParserException("ID, integer, or arithmetic operator expected and "
			          + lookahead.getLexeme() + " found instead");
		 }
	  }
	 
	 private void statement()
	 {
		 if (lookahead.getTokenType() == TokenType.ID){
			 assign();
		 }
		 else if (lookahead.getTokenType() == TokenType.WHILE){
			 whileLoop();
		 }
		 else if (lookahead.getTokenType() == TokenType.IF){
			 ifLoop();
		 }
		 else if (lookahead.getTokenType() == TokenType.REPEAT){
			 repeat();
		 }
		 /*else if (lookahead.token == TokenType.PRINT){
			 print();
		 }*/
	 }
	 
}