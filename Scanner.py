import Look_Up_Table;

# global variables

file = open("sclex1.scl", 'r')  # opens the file and stores it in a file object that can be manipulated later
current_token = []  # list, it will store the token number and lexeme
file_line = ""  # store the current file string being read
col = 0  # current index in the file as it is being read
row = 0  # current row in the file as it is being read
current_line = 0  # current line in the file as it's being read


# def Scanner(file):

def lex():  # lexer
    global row
    global col
    global current_line
    global current_token
    global file_line
    file_line = next_line()
    while(col < len(file_line)):
        if file_line == '':  # skip an empty line
            next_line()
            current_line += 1
            row += 1
        elif file_line[col] == "/":  # checks for comments
            comments()
        elif file_line[col].isalpha(): # check for the current char being a character; isalpha() does NOT work!
            if get_next_char().isaplha():
                for file_line[col] in file_line:
                    if get_next_char() != '':
                        current_token.append()
                    else:
                        return
            else:
                return
        else:
            return
    print(current_token)
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

lex()