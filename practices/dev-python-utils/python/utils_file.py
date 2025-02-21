from pathlib import Path
import os

def get_filename(file_fullname):
    if file_fullname == "":
        raise

    return Path(file_fullname).name


def get_filename_without_extension(file_fullname):
    if file_fullname == "":
        raise

    return Path(file_fullname).stem

def get_filename_extension(file_fullname):
    if file_fullname == "":
        raise

    return Path(file_fullname).suffix

# Extrai arquivos de um diretório raiz, recursivamente
def get_list_files_walk(start_path="."):

    filenames = []

    for root, dirs, files in os.walk(start_path):
        for file in files:
            filename = os.path.join(root, file)
            filenames.append(str(filename))

    return filenames

def change_extension(filename, new_extension):

    ext = get_filename_extension(filename)
    filename = filename.replace(ext, new_extension)
    return str(filename)

def get_path_from_file(filename):

    return str(Path(filename).parents[0])

def search_in_file(filename, str):

    with open(filename) as f:
        for n, l in enumerate(f, 1):
            if str in l:
                return n
            
        f.close()
            
    return -1


if __name__ == '__main__':
    print (search_in_file("D:\\Temp\\Claro\\202406- 860793908_142721924_2024_07_53_2.txt", "Tel;Seção;Data;"))