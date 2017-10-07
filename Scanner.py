import Look_Up_Table
import re

class Lexer:
    col = 0  # current index in the file as it is being read
    row = 0  # current row in the file as it is being read
    current_line = 0  # current line in the file as it's being read
    current_string = ""  # store the current string being read
    Token = []
    file_line = ""
def lex():  # lexer

        global col
        global row
        global current_line
        global current_string
        global file_line
        with open('sclex1.scl', 'r') as file_line:
            space_index = 0  # stores the index that the space is found (in this case the spaces are '#')
            for index in file: # index gives the char, while col gives the # that the index is at
                if index == "":  # skip an empty line
                    next_line()
                    current_line += 1
                    row += 1
                elif index == "/":  # checks for comments
                    comments()
                elif re.search(r'[a-zA-Z]', index):  # check for the current char being a character
                    if re.search(r'[a-zA-Z]', get_next_char()): # check next char for a letter
                        while index != '':
                            current_string = file_line[index]
                        if current_string in Look_Up_Table.Keywords:
                            global Token
                            Token.extend(["Current lexeme", current_string, "Token", Look_Up_Table.Keywords.get(current_string)
                                             , "Value", Look_Up_Table.Keywords[current_string]])
                    elif get_next_char() == '':
                        current_string = file_line[index]
                        Token.extend(["Current lexeme", current_string, "Token", Look_Up_Table.Keywords.get(current_string)
                                             , "Value", Look_Up_Table.Keywords[current_string]])
                elif re.search(r'[0-9]', index): # if it's a digit
                    if re.search(r'[0-9]', get_next_char()):


                else:
                    return

def next_line():  # gets the next line in the file
        global file
        global  current_line
        file.readline()
        current_line += 1
        return file_line

def get_next_char():  # gets the next token
        global col
        global file
        col += 1
        return file_line[col]

def comments(): # interprets whether or not there is a comment, so that it can be ignored
        global file
        global row
        global col
        if file[col] == "/":
            if get_next_char() == "*":
                for char in file:
                    if char == "*" and get_next_char() == "/":
                        row += 1
                        return next_line()
            elif get_next_char() == "/":
                row += 1
                return next_line()
        else:
            return

'''no_spaces = re.sub(r'\s', '#', file_line)  # delete spaces
                        space_index = no_spaces.find('#')
                        for i in range(0, space_index):
                            current_string = file_line[i]'''