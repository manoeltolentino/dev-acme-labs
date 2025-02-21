# To convert PDF files into images
from pdf2image import convert_from_path

# For image preprocessing tasks like deskewing and grayscale conversion.
import cv2

# Destinada a realizar operações em arrays multidimensionais, amigavelmente denominada como ndarray nesta biblioteca.
import numpy as np

# A Python wrapper for Google’s Tesseract OCR engine.
import pytesseract

# Biblioteca padrão muito útil quando se trata de interagir com o sistema operacional.
import os

# Pandas is used to analyze data.
import pandas as pd

# Tratativas com arquivos (desenvolvido)
import utils_file as uf

# Realiza a extração de texto das imagens (páginas) usando OCR
def extract_text_from_file(image):
    text = pytesseract.image_to_string(image)
    return text

# Realiza ajustes nas imagens (rotação, grayscale etc.)
def deskew(image):

    # Transfer image of pdf_file into array
    page_arr = np.asarray(image)
    # Transfer into grayscale
    page_arr_gray = cv2.cvtColor(page_arr, cv2.COLOR_BGR2GRAY)

    gray = cv2.bitwise_not(page_arr_gray)
    coords = np.column_stack(np.where(gray > 0))
    angle = cv2.minAreaRect(coords)[-1]

    if angle < -45:
        angle = -(90 + angle)
    else:
        angle = -angle

    (h, w) = page_arr.shape[:2]
    center = (w // 2, h //2)
    M = cv2.getRotationMatrix2D(center, angle, 1.0)
    rotated = cv2.warpAffine(page_arr, M, (w, h), flags=cv2.INTER_CUBIC, borderMode=cv2.BORDER_REPLICATE)

    return rotated


def process_page(page):
    try:
        # Debug
        print("Página " + str(page_count))
        page.save("{0}imagem-{1}.png".format(path_out, page_count))

        # Deskew the page
        page_deskew = deskew(page)

        text = extract_text_from_file(page)

        with open("{0}imagem-{1}.txt".format(path_out, page_count), "w") as text_file:
            text_file.write(text)

        return text

    except Exception as e:
        print(e)

        # If can't extract then give some notes into df
        if hasattr(e, 'message'):
            return -1, e.message
        else:
            return -1, str(e)        


def process_file(pdf_file):

    if not str(pdf_file).lower().endswith(".pdf"):
        print("Arquivo ignorado: " + pdf_file)
        return

    # Debug
    global file_count
    file_count += 1
    
    global path_out
    path_out = path + "file {0}".format(file_count) + "\\"
    if not os.path.exists(path_out):
        os.makedirs(path_out)


    print("*** Processando arquivo " + pdf_file)

    # Converte PDF em imagens
    # Informar o diretório onde foi instalado o poppler-24.07.0
    pages = convert_from_path(pdf_file, poppler_path='D:\\Files\\Dev\\Libs\\poppler-24.07.0\\Library\\bin')

    extracted_text = []

    # Converte imagens em textos (OCR)
    for page_pdf in pages:

        global page_count
        page_count += 1

        text = process_page(page_pdf)

        extracted_text.append(text)

    # Grava textos OCR em arquivo
#    with open(path + "file {0}.txt".format(file_count), "w") as text_file:
    with open(uf.change_extension(pdf_file, "-ocr.txt"), "w") as text_file:
        for line in extracted_text:
            line_out = str(line)
#            print("Linha: " + line_out)
            text_file.write(line_out + "\n")


# Lê arquivos do diretório informado
# Informar o diretório root
dir_files = "D:\\Temp\\OCR"

pdf_file_list = uf.get_list_files_walk(dir_files)

# Debug
file_count = 0


for file_item in pdf_file_list:
    page_count = 0

    # Extrai caminho do arquivo informado. Útil para gerar os arquivos de saída (no mesmo diretório)
    path, filename = os.path.split(file_item)
    path += "\\"
    path_out = ""

    process_file(file_item)

print("Fim do processamento")
