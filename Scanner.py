import Look_Up_Table;

# global variables

file = open("sclex1.scl", 'r')  # opens the file and stores it in a file object that can be manipulated later
current_token = ()  # tuple, it will store the token number and lexeme
file_line = ""  # store the current file string being read
col = 0  # current index in the file as it is being read
row = 0  # current row in the file as it is being read
current_line = 0  # current line in the file as it's being read


# def Scanner(file):

# def lex():  # lexer

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

def comments(file_line): # interprets whether or not there is a comment, so that it can be ignored
    if file_line[col] == "/":
        if get_next_char() == "*":
            for char in file_line:
                if char == "*" and get_next_char() == "/":
                    return next_line()
        elif get_next_char() == "/":
            return next_line()
    else:
        return