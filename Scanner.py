import Look_Up_Table
import re
import collections

class Lexer:

   ''' file = open("sclex1.scl", 'r')  # opens the file and stores it in a file object that can be manipulated later
    Token = collections.namedtuple('Token', ['type', 'tok_type', 'value', 'line'])  # list, it will store the token number and lexeme
    file_line = file.readlines()  # store the current file string being read '''

def lex(self, file):  # lexer

        col = 0  # current index in the file as it is being read
        row = 0  # current row in the file as it is being read
        current_line = 0  # current line in the file as it's being read
        current_string = ""  # store the current string being read

        space_index = 0  # stores the index that the space is found (in this case the spaces are '#')
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

    next_line()
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
    