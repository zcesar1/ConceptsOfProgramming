import Look_Up_Table;

# global variables

file = open("sclex1.scl", 'r')  # opens the file and stores it in a file object that can be manipulated later
current_token = ()  # tuple, it will store the token number and lexeme
file_line = ""  # store the current file string being read
col = 0  # current index in the file as it is being read
row = 0  # current row in the file as it is being read
current_index_value = file_line  # will store the current value that the array is at in the string
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
    global current_index_value
    current_index_value = file_line[col]
    return file_line

print (file_line)
next_line()
print (file_line)
next_line()
print (file_line)

print ("current_index_value", current_index_value)

def get_next_char():  # gets the next token
    global current_index_value
    global col
    global file_line
    index = 0
    while(index < len(file_line)):
        if index == col:
            col += 1
            index += 1
            return current_index_value
            break
        else:
            return
get_next_char()
print ("next char = ", get_next_char())
get_next_char()
print ("next char = ", get_next_char())

# def comments(): # interprets whether or not there is a comment, so that it can be ignored
  #  if current_index_value == "/":
   #     if get_next_char() == "*":
            # keep going until you reach * and the next character is a /
    #    if get_next_char() == "/"
    #        next_line()
   # else:
    #    return
