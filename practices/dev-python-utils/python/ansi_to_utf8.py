#to support encodings
import codecs

import os

def get_files_java(root):

    f = []

    for path, subdirs, files in os.walk(root):
        for name in files:
            if (name.endswith("java")):
                print(os.path.join(path, name))
                f.append(os.path.join(path, name))

    return f


#replace
root = "D:\\Temp\\ansi"

for f in get_files_java(root):
    #read input file
    with codecs.open(f, 'r', encoding = 'mbcs') as file:
        lines = file.read()

    #write output file
    with codecs.open(f, 'w', encoding = 'utf8') as file:
        file.write(lines)