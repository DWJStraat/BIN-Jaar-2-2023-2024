"""
A simple class for parsing PDF files.
Created on 2024-April-21 by DWJStraat
"""

import unittest

from pypdf import PdfReader


class Reader:
    """
    A class for parsing PDF files.
    """

    def __init__(self, file_path: str):
        self.file_path = file_path
        self.pdf = PdfReader(file_path)

    def pages(self) -> int:
        """
        Returns the number of pages in the PDF
        :return: (Int) the number of pages in the PDF
        """
        return len(self.pdf.pages)

    def extract_text(self) -> str:
        """
        Extracts the string from the PDF file and returns
        the data as a string
        :return: (Str) content of the PDF file
        """
        contents = ""
        for page in self.pdf.pages:
            contents += page.extract_text()
        return str(contents)


class ReaderTest(unittest.TestCase):
    def setUp(self):
        """
        Sets up the test
        """
        self.reader = Reader('test_file.pdf')

    def test_pages(self):
        self.assertEqual(self.reader.pages(), 33)

    def test_extract_text(self):
        content = self.reader.extract_text().strip()
        self.assertTrue(content.startswith('Mus Musculus'))
        self.assertTrue(content.endswith("ACCGGCCTAA"))


if __name__ == '__main__':
    unittest.main()
