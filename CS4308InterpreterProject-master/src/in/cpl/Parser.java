/*
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
			 if (lookahead.token == Token.DEFINE){
				 assign();
			 }
			 else if (lookahead.token == Token.FUNCTION){
				 functionLoop();
			 }
			 else if (lookahead.token == Token.BEGIN){
				 beginLoop();
			 }
			 else if (lookahead.token == Token.WHILE){
				 whileLoop();
			 }
			 else if (lookahead.token == Token.IF){
				 ifLoop();
			 }
			 else if (lookahead.token == Token.FOR){
				 forLoop();
			 }
			 else if (lookahead.token == Token.INPUT){
				 input();
			 }
			 else if (lookahead.token == Token.CONSTANTS){
				 constants();
			 }
			 else if (lookahead.token == Token.VARIABLES){
				 variables();
			 }
			 else if (lookahead.token == Token.SET){
				 set();
			 }
			 else if (lookahead.token == Token.PARAMETERS){
				 parameters();
			 }
		 }
	  }
	 
	 private void assign()
	  {
		// define varName = value type typeValue
		// define varName of type typeValue
		 nextToken();
		 if(!lookahead.token == Token.IDENTIFIER){
			 throw new ParserException("Variable name expected and "
			          + lookahead.sequence + " found instead");
		 }
		 else{
			 nextToken();
		 }
		 
		 if(lookahead.token == Token.EQUAL_SIGN){
			 nextToken();
			 if(!lookahead.token == Token.INTEGER){
				 throw new ParserException("Integer expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
			 if(!lookahead.token == Token.TYPE){
				 throw new ParserException("Type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
			 if(!lookahead.token == Token.INTEGER_TYPE){
				 throw new ParserException("Integer type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
		 }
		 else if(lookahead.token == Token.OF){
			 nextToken();
			 if(!lookahead.token == Token.TYPE){
				 throw new ParserException("Type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
			 if(!lookahead.token == Token.INTEGER_TYPE){
				 throw new ParserException("Integer type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
		 }
		 else{
			 throw new ParserException("Equals or Of expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void functionLoop()
	  {
		 //function funName is ... endfun main
		//function funName return type integer ... endfun main
		 nextToken();
		 if(lookahead.token == Token.IS){
			 nextToken();
			 while(!lookahead.token == Token.END_FUNCTION){
				 expression();
			 }
			 nextToken();
			 if(!lookahead.token == Token.MAIN){
				 throw new ParserException("Main expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
		 }
		 else if(lookahead.token == Token.RETURN){
			 nextToken();
			 if(lookahead.token == Token.TYPE){
				 nextToken(); 
			 }
			 else{
				 throw new ParserException("Type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 if(lookahead.token == Token.INTEGER_TYPE){
				 nextToken(); 
			 }
			 else{
				 throw new ParserException("Integer type expected and "
				          + lookahead.sequence + " found instead");
			 }
			 while(!lookahead.token == Token.END_FUNCTION){
				 expression();
			 }
			 nextToken();
			 if(!lookahead.token == Token.MAIN){
				 throw new ParserException("Main expected and "
				          + lookahead.sequence + " found instead");
			 }
			 else{
				 nextToken();
			 }
		 }
		 else{
			 throw new ParserException("Is or return expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void whileLoop()
	  {
		 //while ... endwhile
		 nextToken();
		 while(!lookahead.token == Token.END_WHILE){
			 expression();
		 }
		 nextToken();
	  }
	 
	 private void ifLoop()
	  {
		 //if ... endif
		 nextToken();
		 while(!lookahead.token == Token.END_IF){
			 expression();
		 }
		 nextToken();
	  }
	 
	 private void beginLoop()
	  {
		 //begin ... exit
		 //begin ... return var
		 nextToken();
		 while(!lookahead.token == Token.EXIT  || !lookahead.token == Token.RETURN){
			 expression();
		 }
		 if(lookahead.token == Token.EXIT){
			 nextToken(); 
		 }
		 else if(lookahead.token == Token.RETURN){
			 nextToken();
			 if(lookahead.token == Token.IDENTIFIER){
				 nextToken(); 
			 }
			 else{
				 throw new ParserException("Variable expected and "
				          + lookahead.sequence + " found instead");
			 }
		 }
	  }
	 
	 private void forLoop()
	  {
		 //for ... endfor
		 nextToken();
		 while(!lookahead.token == Token.END_FOR){
			 expression();
		 }
		 nextToken();
	  }
	 
	 private void input()
	  {
		 //input "input string", variable
		 nextToken();
		 if(lookahead.token == Token.STRING){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("String expected and "
			          + lookahead.sequence + " found instead");
		 }
		 if(lookahead.token == Token.IDENTIFIER){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Variable expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void constants()
	  {
		 //constants define ... integer
		 nextToken();
		 if(lookahead.token == Token.DEFINE){
			 assign(); 
		 }
		 else{
			 throw new ParserException("Define expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void variables()
	  {
		 //variables define ... integer
		 nextToken();
		 if(lookahead.token == Token.DEFINE){
			 assign(); 
		 }
		 else{
			 throw new ParserException("Define expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void set()
	  {
		 //set varName = value(other varName)
		 nextToken();
		 if(lookahead.token == Token.IDENTIFIER){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Variable expected and "
			          + lookahead.sequence + " found instead");
		 }
		 if(lookahead.token == Token.EQUAL_SIGN){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Equal sign expected and "
			          + lookahead.sequence + " found instead");
		 }
		 if(lookahead.token == Token.IDENTIFIER || lookahead.token == Token.INTEGER || lookahead.token == Token.FLOAT || lookahead.token == Token.STRING){
			 nextToken(); 
		 }
		 else{
			 throw new ParserException("Value expected and "
			          + lookahead.sequence + " found instead");
		 }
	  }
	 
	 private void parameters()
	  {
		 //parameters varName ... integer
		 assign();
	  }
	 
}
*/