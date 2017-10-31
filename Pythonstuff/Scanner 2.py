""" Scanner """

from collections import OrderedDict

# SCANNER

# Token types
IDENTIFIER = 'IDENTIFIER'
CHAR_LIST = 'CHAR_LIST'
LITERAL_INTEGER = 'LITERAL_INTEGER'
ASSIGNMENT_OPERATOR = 'ASSIGNMENT_OPERATOR'
LE_OPERATOR = 'LE_OPERATOR'
GE_OPERATOR = 'GE_OPERATOR'
GT_OPERATOR = 'GT_OPERATOR'
EQ_OPERATOR = 'EQ_OPERATOR'
NE_OPERATOR = 'NE_OPERATOR'
ADD_OPERATOR = 'ADD_OPERATOR'
SUB_OPERATOR = 'SUB_OPERATOR'
MUL_OPERATOR = 'MUL_OPERATOR'
DIV_OPERATOR = 'DIV_OPERATOR'
POW_OPERATOR = 'POW_OPERATOR'
FLOAT = 'FLOAT'
STRING = 'STRING'
ID = 'ID'
SEMICOLON = 'SEMICOLON'
COLON = 'COLON'
COMMA = 'COMMA'
LPAREN = 'LPAREN'
RPAREN = 'RPAREN'
DOT = 'DOT'
EOF = 'EOF'



class Token(object):
    def __init__(self, type, value):
        self.type = type
        self.value = value

    def __str__(self):
        """String representation of the class instance.

        Examples:
            Token(INTEGER, 3)
            Token(PLUS, '+')
            Token(MUL, '*')
        """
        return 'Token({type}, {value})'.format(
            type=self.type,
            value=repr(self.value)
        )

    def __repr__(self):
        return self.__str__()


RESERVED_KEYWORDS = {
    'specifications': Token('SPECIFICATIONS', 'specifications'),
    'symbol': Token('SYMBOL', 'symbol'),
    'forward': Token('FORWARD', 'forward'),
    'references': Token('REFERENCES', 'references'),
    'function': Token('FUNCTION', 'function'),
    'pointer': Token('POINTER', 'pointer'),
    'array': Token('ARRAY', 'array'),
    'type': Token('TYPE', 'type'),
    'struct': Token('STRUCT', 'struct'),
    'integer': Token('INTEGER', 'integer'),
    'enum': Token('ENUM', 'enum'),
    'global': Token('GLOBAL', 'global'),
    'declarations': Token('DECLARATIONS', 'declarations'),
    'implementations': Token('IMPLEMENTATIONS', 'implementations'),
    'main': Token('MAIN', 'main'),
    'parameters': Token('PARAMETERS', 'parameters'),
    'constant': Token('CONSTANT', 'constant'),
    'begin': Token('BEGIN', 'begin'),
    'endfun': Token('ENDFUN', 'endfun'),
    'if': Token('IF', 'if'),
    'then': Token('THEN', 'then'),
    'else': Token('ELSE', 'else'),
    'endif': Token('ENDIF', 'endif'),
    'repeat': Token('REPEAT', 'repeat'),
    'until': Token('UNTIL', 'until'),
    'endrepeat': Token('ENDREPEAT', 'endrepeat'),
    'display': Token('DISPLAY', 'display'),
    'set': Token('SET', 'set'),
    'return': Token('RETURN', 'return'),
}


class Lexer(object):
    def __init__(self, text):
        # client string input, e.g. "4 + 2 * 3 - 6 / 2"
        self.text = text
        # self.pos is an index into self.text
        self.pos = 0
        self.current_char = self.text[self.pos]

    def error(self):
        raise Exception('Invalid character')

    def advance(self):
        """Advance the `pos` pointer and set the `current_char` variable."""
        self.pos += 1
        if self.pos > len(self.text) - 1:
            self.current_char = None  # Indicates end of input
        else:
            self.current_char = self.text[self.pos]

    def peek(self):
        peek_pos = self.pos + 1
        if peek_pos > len(self.text) - 1:
            return None
        else:
            return self.text[peek_pos]

    def skip_whitespace(self):
        while self.current_char is not None and self.current_char.isspace():
            self.advance()

    def skip_line_comment(self):
        #if self.current_char == '/' and self.peek() == '/':
            # advance until end of line

    def skip_comment(self):
        while self.current_char != '*' and self.peek() != '/':
            self.advance()
        self.advance() # *
        self.advance() # /

    def number(self):
        """Return a (multidigit) integer or float consumed from the input."""
        result = ''
        while self.current_char is not None and self.current_char.isdigit():
            result += self.current_char
            self.advance()

        if self.current_char == '.':
            result += self.current_char
            self.advance()

            while (
                self.current_char is not None and
                self.current_char.isdigit()
            ):
                result += self.current_char
                self.advance()

            token = Token('FLOAT', float(result))
        else:
            token = Token('LITERAL_INTEGER', int(result))

        return token

    def _id(self):
        """Handle identifiers and reserved keywords"""
        result = ''
        while self.current_char is not None and self.current_char.isalnum():
            result += self.current_char
            self.advance()

        token = RESERVED_KEYWORDS.get(result.lower())
        return token

    def get_next_token(self):
        """Lexical analyzer (also known as scanner or tokenizer)

        This method is responsible for breaking a sentence
        apart into tokens. One token at a time.
        """
        while self.current_char is not None:

            if self.current_char.isspace():
                self.skip_whitespace()
                continue

            if self.current_char == '/' and self.peek() == '*':
                self.advance() # /
                self.advance() # *
                self.skip_comment()
                continue

            if self.current_char == '/' and self.peek() == '/':
                self.advance() # /
                self.advance() # /
                self.skip_line_comment()
                continue

            if self.current_char.isalpha():
                return self._id()

            if self.current_char.isdigit():
                return self.number()

            if self.current_char == '=':
                self.advance()
                return Token(ASSIGNMENT_OPERATOR, '=')

            if self.current_char == ';':
                self.advance()
                return Token(SEMICOLON, ';')

            if self.current_char == ':':
                self.advance()
                return Token(COLON, ':')

            if self.current_char == ',':
                self.advance()
                return Token(COMMA, ',')

            if self.current_char == '+':
                self.advance()
                return Token(ADD_OPERATOR, '+')

            if self.current_char == '-':
                self.advance()
                return Token(SUB_OPERATOR, '-')

            if self.current_char == '*':
                self.advance()
                return Token(MUL_OPERATOR, '*')

            if self.current_char == '/':
                self.advance()
                return Token(DIV_OPERATOR, '/')

            if self.current_char == '(':
                self.advance()
                return Token(LPAREN, '(')

            if self.current_char == ')':
                self.advance()
                return Token(RPAREN, ')')

            if self.current_char == '.':
                self.advance()
                return Token(DOT, '.')

            self.error()

        return Token(EOF, None)




def main():
    import sys
    text = open(sys.argv[1], 'r').read()

    lexer = Lexer(text)
    #parser = Parser(lexer)
    #tree = parser.parse()

    #semantic_analyzer = SemanticAnalyzer()
    #try:
    #    semantic_analyzer.visit(tree)
    #except Exception as e:
    #    print(e)

    # interpreter = Interpreter(tree)
    # result = interpreter.interpret()
    # print('')
    # print('Run-time GLOBAL_MEMORY contents:')
    # for k, v in sorted(interpreter.GLOBAL_MEMORY.items()):
    #     print('%s = %s' % (k, v))


if __name__ == '__main__':
    main()

