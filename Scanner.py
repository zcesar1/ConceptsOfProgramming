import Look_Up_Table
import re

class Lexer:
    col = 0  # current index in the file as it is being read
    row = 0  # current row in the file as it is being read
    current_line = 0  # current line in the file as it's being read
    current_string = ""  # store the current string being read
    Token = []
    file_line = ""
    dict_value = "" # be the key for the dict values that are operators
def lex():  # lexer

        global col
        global row
        global current_line
        global current_string
        global file_line
        with open('sclex1.scl', 'r') as file_line:
            for index in file_line: # index gives the char, while col gives the # that the index is at
                if index == "":  # skip an empty line
                    # next_line()
                    current_line += 1
                    row += 1
                elif index == "/":  # checks for comments
                    comments()
                elif re.search(r'[a-zA-Z]', index):  # check for the current char being a character
                    if re.search(r'[a-zA-Z]', get_next_char()):  # check next char for a letter
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
                        while index != '':
                            current_string = file_line[index]
                        Token.extend(["Current lexeme", current_string, "Token", "int-literal"
                                             , "Value", Look_Up_Table.Keywords['integer identifier']])
                elif re.search(r'[+/*-]', index):
                    current_string = file_line[index]
                    dict_value = lookup(current_string)
                    Token.extend(["Current lexeme", current_string, "Token", Look_Up_Table.Operators.get(dict_value)
                                             , "Value", Look_Up_Table.Keywords[dict_value]])
                else:
                    return
            return Token
def lookup(current_string): # find the corresponding dict values for non-keywords and operators
    global dict_value
    if current_string == '+':
        dict_value = "add_operator"
        return dict_value
    if current_string == '-':
        dict_value = "sub_operator"
        return dict_value
    if current_string == '*':
        dict_value = "mul_operator"
        return dict_value
    if current_string == '/':
        dict_value = "div_operator"
        return dict_value
    if current_string == '(':
        dict_value = "left_paren"
        return dict_value
    if current_string == ')':
        dict_value = "right_paren"
        return dict_value
    if current_string == '<=':
        dict_value = "le_operator"
        return dict_value
    if current_string == '<':
        dict_value = "lt_operator"
        return dict_value
    if current_string == '>=':
        dict_value = "ge_operator"
        return dict_value
    if current_string == '>':
        dict_value = "gt_operator"
        return dict_value
    if current_string == '=':
        dict_value = "eq_operator"
        return dict_value
    if current_string == '!=':
        dict_value = "ne_operator"
        return dict_value
def next_line():  # gets the next line in the file
        global file_line
        global current_line
        file_line.readline()
        current_line += 1
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
        if file[col] == "/":
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