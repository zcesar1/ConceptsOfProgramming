package in.cpl;

import jdk.nashorn.internal.parser.Token;
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
			 tokens.pop();
		 }
		 else{
			 System.out.println("Token list empty");
		 }
	  }
	 
	 private void expression()
	  {
		 if (lookahead.token == Token.DEFINE){
			 assign();
		 }
		 else if (lookahead.token == Token.BEGIN){
			 beginLoop();
		 }
		 else if (lookahead.token == Token.WHILE){
			 whileLoop();
		 }
	  }
	 
	 private void assign()
	  {
	    if (lookahead.token == Token.DEFINE)
	    {
	      // define varName = value type typeValue
	      nextToken();
	      //term();
	      //sumOp();
	    }
	    else
	    {
	    }
	  }
	 
	 private void beginLoop()
	  {
	    if (lookahead.token == Token.DEFINE)
	    {
	      // define varName = value type typeValue
	      nextToken();
	      //term();
	      //sumOp();
	    }
	    else
	    {
	    }
	  }
	 
	 private void whileLoop()
	  {
	    if (lookahead.token == Token.DEFINE)
	    {
	      // define varName = value type typeValue
	      nextToken();
	      //term();
	      //sumOp();
	    }
	    else
	    {
	    }
	  }
	 
	 
	 
	 
	 
	  private void signedTerm()
	  {
	    if (lookahead.token == Token.PLUSMINUS)
	    {
	      // signed_term -> PLUSMINUS term
	      nextToken();
	      term();
	    }
	    else
	    {
	      // signed_term -> term
	      term();
	    }
	  }
	 
	 private void term()
	  {
	    // term -> factor term_op
	    factor();
	    termOp();
	  }

	  private void termOp()
	  {
	    if (lookahead.token == Token.MULTDIV)
	    {
	      // term_op -> MULTDIV factor term_op
	      nextToken();
	      signedFactor();
	      termOp();
	    }
	    else
	    {
	      // term_op -> EPSILON
	    }
	  }

	  private void signedFactor()
	  {
	    if (lookahead.token == Token.PLUSMINUS)
	    {
	      // signed_factor -> PLUSMINUS factor
	      nextToken();
	      factor();
	    }
	    else
	    {
	      // signed_factor -> factor
	      factor();
	    }
	  }
	  
	  private void factor()
	  {
	    // factor -> argument factor_op
	    argument();
	    factorOp();
	  }

	  private void factorOp()
	  {
	    if (lookahead.token == Token.RAISED)
	    {
	      // factor_op -> RAISED expression
	      nextToken();
	      signedFactor();
	    }
	    else
	    {
	      // factor_op -> EPSILON
	    }
	  }
	  
	  private void argument()
	  {
	    if (lookahead.token == Token.FUNCTION)
	    {
	      // argument -> FUNCTION argument
	      nextToken();
	      argument();
	    }
	    else if (lookahead.token == Token.OPEN_BRACKET)
	    {
	      // argument -> OPEN_BRACKET sum CLOSE_BRACKET
	      nextToken();
	      expression();

	      if (lookahead.token != Token.CLOSE_BRACKET)
	        throw new ParserException("Closing brackets expected and "
	          + lookahead.sequence + " found instead");

	      nextToken();
	    }
	    else
	    {
	      // argument -> value
	      value();
	    }
	  }
	  
	  private void value()
	  {
	    if (lookahead.token == Token.NUMBER)
	    {
	      // argument -> NUMBER
	      nextToken();
	    }
	    else if (lookahead.token == Token.VARIABLE)
	    {
	      // argument -> VARIABLE
	      nextToken();
	    }
	    else
	    {
	      throw new ParserException(
	        "Unexpected symbol "+lookahead.sequence+" found");
	    }
	  }
	 
}
