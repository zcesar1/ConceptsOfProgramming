# Concepts of Programming Languages Project 1 Deliverable
# Zoe Cesar, Kelli Yeatman, Vlad Perevalov

import Look_Up_Table
import re

class Lexer:
    col = 0  # current index in the file as it is being read
    row = 0  # current row in the file as it is being read
    current_string = ""  # store the current string being read
    Token = ['List of Tokens']
    file = open('sclex1.scl', 'r')
    file_line = file
    dict_value = "" # be the key for the dict values that are operators

    def lex(self):  # lexer
            col = Lexer.col
            row = Lexer.row
            current_string = Lexer.current_string
            file_line = Lexer.file_line
            Token = Lexer.Token
            token1 = []
            token2 = []
            token3 = []
            token4 = []
            token5 = []
            token6 = []
            string = ""
            # for index in file_line: # index gives line of the file
            for line in file_line:  # prints the file line (ends with \n)
                while(re.search(r'[\s]]', file_line[col])):
                    print(line[col])
                    col += 1
               # for letter in line:

                ''' if letter == "/":  # checks for comments
                        self.comments(file_line, col)

                    #   re.search(r'[a-zA-Z]', letter)
                    #  re.search(r'[a-zA-Z]', self.get_next_char(line, col))
                    elif letter.isalpha():  # check for the current char being a letter letter.isalpha()
                       # if : # check if the next char is a letter
                        #col += 1
                        current_string += letter
                        col += 1

                    # elif not letter.isalpha():
                    #     break

                    elif current_string in Look_Up_Table.Keywords:
                        token1.extend(["Current lexeme", current_string, "Token",
                                           Look_Up_Table.Keywords.get(current_string)
                                                  , "Value", Look_Up_Table.Keywords[current_string]])
                        col += 1

                    elif current_string == 'import':
                        token2.extend(["Current lexeme", current_string, "Token",
                                                   Look_Up_Table.Keywords.get('specifications')
                                                  , "Value", Look_Up_Table.Keywords['specifications']])
                        col += 1

                    elif current_string.isupper():
                        token3.extend(["Current lexeme", current_string, "Token",
                                           Look_Up_Table.Non_Keywords.get('constant')
                                                  , "Value", Look_Up_Table.Keywords['constant']])
                        col += 1

                    elif letter == '"':
                        if self.get_next_char(line, col).isalpha():
                            print col
                            col += 1
                            string += letter
                        else:
                            string += letter
                            col += 1
                return current_string

'''
    ''' 

elif self.get_next_char(file_line, col) == '':
     col += 1
     current_string = line
     token4.extend(["Current lexeme", current_string, "Token", Look_Up_Table.Non_Keywords.get('ID')
                             , "Value", Look_Up_Table.Keywords['ID']])
elif re.search(r'[0-9]', line): # if it's a digit
        while file_line != '':
            col += 1
            current_string = file_line[file_line]
        token5.extend(["Current lexeme", current_string, "Token", "int-literal"
                             , "Value", Look_Up_Table.Keywords['integer identifier']])
elif re.search(r'[+/*-]', line):
    col += 1
    current_string = file_line[file_line]
    dict_value = self.lookup(current_string)
    token6.extend(["Current lexeme", current_string, "Token", Look_Up_Table.Operators.get(dict_value)
                             , "Value", Look_Up_Table.Keywords[dict_value]])
elif line == "":
    self.next_line()
Token.extend(token1)
Token.extend(token2)
Token.extend(token3)
Token.extend(token4)
Token.extend(token5)
Token.extend(token6)
return Token
'''
    def lookup(self, current_string): # find the corresponding dict values for non-keywords and operators
        dict_value = Lexer.dict_value
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

    def next_line(self):  # gets the next line in the file
            self.file_line.readline()
            self.row += 1
            return self.file_line

    def get_next_char(self, line, col):
        return line[col + 1]

    def comments(self, file_line, col): # interprets whether or not there is a comment, so that it can be ignored
            row = Lexer.row
            for line in file_line:
                if line[col] == "/":
                    if self.get_next_char(file_line, col) == "*":
                        for char in file_line:
                            if char == "*" and self.get_next_char(file_line, col) == "/":
                                row += 1
                                return self.next_line()
                    elif self.get_next_char(file_line, col) == "/":
                        row += 1
                        return self.next_line()
                else:
                    return