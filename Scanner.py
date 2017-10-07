import Look_Up_Table
import re
import collections
# global variables

file = open("sclex1.scl", 'r')  # opens the file and stores it in a file object that can be manipulated later
Token = collections.namedtuple('Token', ['typ', 'value', 'line', 'column'])  # list, it will store the token number and lexeme
file_line = file.read()  # store the current file string being read
col = 0  # current index in the file as it is being read
row = 0  # current row in the file as it is being read
current_line = 0  # current line in the file as it's being read
current_string = "" # store the current string being read
# def Scanner(file):

def lex(s):  # lexer
    '''global row
    global col
    global current_line
    global Token
    global file_line
    global current_string'''
    token_specification = [
        ('NUMBER', r'\d+(\.\d*)?'),  # Integer or decimal number
        ('ASSIGN', r':='),  # Assignment operator
        ('ID', r'[A-Za-z]+'),  # Identifiers
        ('OP', r'[+*\/\-]'),  # Arithmetic operators
        ('NEWLINE', r'\n'),  # Line endings
        ('SKIP', r'[ \t]'),  # Skip over spaces and tabs
        ('PAREN', r'(\w\s)')  # parentheses
    ]
    tok_regex = '|'.join('(?P<%s>%s)' % pair for pair in token_specification)
    get_token = re.compile(tok_regex).match

    line = 1
    pos = line_start = 0
    mo = get_token(s)
    while mo is not None:
        typ = mo.lastgroup
        if typ == 'NEWLINE':
            line_start = pos
            line += 1
        elif typ != 'SKIP':
            val = mo.group(typ)
            if typ == 'ID' and val in Look_Up_Table.Keywords:
                typ = val
            yield Token(typ, val, line, mo.start() - line_start)
        pos = mo.end()
        mo = get_token(s, pos)
    if pos != len(s):
        raise RuntimeError('Unexpected character %r on line %d' % (s[pos], line))

    '''space_index = 0  # stores the index that the space is found (in this case the spaces are '#')
    if file_line == "":  # skip an empty line
        next_line()
        current_line += 1
        row += 1
    elif file_line[col] == "/":  # checks for comments
        comments()
    elif re.search(r'[a-zA-Z]', file_line[col]):  # check for the current char being a character
        if re.search(r'[a-zA-Z]', get_next_char()):
            no_spaces = re.sub(r'\s', '#', file_line)  # delete spaces
            space_index = no_spaces.find('#')
            for i in range(0, space_index):
                current_string = file_line[i]
        if(Look_Up_Table.Keywords.has_key(current_string)):
            Token.append(["Current lexeme", current_string, "Token", Look_Up_Table.Keywords.get(current_string), "Value",
                          Look_Up_Table.Keywords[current_string]])
        else:
            return
    else:
        return
'''
def next_line():  # gets the next line in the file
    global current_line
    if current_line == 0:
        current_line = 1
    else:
        current_line += 1
    global file_line
    file_line = file.readline()
    return file_line

def get_next_char():  # gets the next token
    global col
    global file_line
    col += 1
    return file_line[col]

def comments(): # interprets whether or not there is a comment, so that it can be ignored
    global file_line
    global row
    global col
    if file_line[col] == "/":
        if get_next_char() == "*":
            for char in file_line:
                if char == "*" and get_next_char() == "/":
                    row += 1
                    return next_line()
        elif get_next_char() == "/":
            row += 1
            return next_line()
    else:
        return

'''next_line()
print(get_next_char())
if re.search(r'[a-zA-Z]', file_line[col]):  # check for the current char being a character
        if re.search(r'[a-zA-Z]', get_next_char()):
            no_spaces = re.sub(r'\s', '#', file_line)  # delete spaces
            space_index = no_spaces.find('#')
            for i in range(0, space_index):
                current_string = file_line[i]
        if(Look_Up_Table.Keywords.has_key(current_string)):
            Token.append(["Current lexeme", current_string, "Token", Look_Up_Table.Keywords.get(current_string), "Value",
                          Look_Up_Table.Keywords[current_string]])
'''
#  print ('[%s]' % ', '.join(map(str, Token)))
for token in lex(file_line):
    print(token)