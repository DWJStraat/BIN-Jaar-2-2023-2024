"""
A class that utilises NLTK package to pre-process string
Based on code written by Varrel Tantio on 2023-September-22 at
https://python.plainenglish.io/text-mining-in-python-steps-and-examples-645b16c6bc5d
Created on 2024-April-21 by DWJStraat
"""
import unittest

import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize


class PreProcessing:
    """
    A class to clean up a string using NLTK
    """

    def __init__(self):
        self.stop_words = stopwords.words('english')

    @staticmethod
    def download():
        """
        Downloads the most recent NLTK files
        """
        nltk.download('stopwords')
        nltk.download('punkt')
        nltk.download('wordnet')

    @staticmethod
    def tokenize(string: str) -> list:
        """
        Tokenizes a string
        :param string: (Str) The string to be tokenized
        :return: (List) A list containing the tokenized string
        """
        return word_tokenize(string.lower())

    @staticmethod
    def stem(word: str) -> str:
        """
        Stems a word.
        :param word: (Str) The word to be stemmed
        :return: (Str) The stemmed word
        """
        return nltk.PorterStemmer().stem(word)

    def stem_loop(self, tokens: list) -> list:
        """
        Stems a tokenized string
        :param tokens: (List) A tokenized string
        :return: (List) A list containing the stemmed tokenized string
        """
        output = []
        for word in tokens:
            if word not in self.stop_words:
                output.append(self.stem(word))
        return output

    @staticmethod
    def lemmatize(word: str) -> str:
        """
        Lemmatizes a word
        :param word: (Str) The word to be lemmatized
        :return: (Str) The lemmatized word
        """
        return nltk.WordNetLemmatizer().lemmatize(word)

    def lemmatize_loop(self, tokens: list) -> list:
        """
        Lemmatizes a tokenized string
        :param tokens: (List) A tokenized string
        :return: (List) A list containing the lemmatized tokenized string
        """
        output = []
        for word in tokens:
            if word not in self.stop_words:
                output.append(self.lemmatize(word))
        return output

    def preprocess(self, string: str, lemmatize: bool = True) -> list:
        """
        Pre-processes a string.
        :param string: (Str) The string to be preprocessed
        :param lemmatize: (Bool) Whether to use lemmatization (True) or
        stemming (False). Defaults to True
        :return: (List) A list containing the preprocessed string
        """
        tokens = self.tokenize(string.lower())
        if lemmatize:
            return self.lemmatize_loop(tokens)
        else:
            return self.stem_loop(tokens)


class PreProcessingTest(unittest.TestCase):
    def setUp(self):
        """
        Initializes the test case data
        """
        self.text = ("Text mining is a fascinating field for natural language "
                     "processing enthusiasts.")
        self.tokenized = ['text', 'mining', 'is', 'a', 'fascinating', 'field',
                          'for', 'natural', 'language', 'processing',
                          'enthusiasts', '.']
        self.lemmatized = ['text', 'mining', 'is', 'a', 'fascinating', 'field',
                           'for', 'natural', 'language', 'processing',
                           'enthusiast',
                           '.']
        self.stemmed = ['text', 'mine', 'is', 'a', 'fascin', 'field', 'for',
                        'natur', 'languag', 'process', 'enthusiast', '.']
        self.preprocess = PreProcessing()

    def test_tokenize(self):
        result = self.preprocess.tokenize(self.text)
        self.assertEqual(result, self.tokenized)

    def test_lemmatize(self):
        for index in range(len(self.tokenized)):
            word = self.tokenized[index]
            lemmatized = self.lemmatized[index]
            result = self.preprocess.lemmatize(word)
            self.assertEqual(result, lemmatized)

    def test_stem(self):
        for index in range(len(self.tokenized)):
            word = self.tokenized[index]
            stemmed = self.stemmed[index]
            result = self.preprocess.stem(word)
            self.assertEqual(stemmed, result)

    def test_preprocess_stem(self):
        expected = ['text', 'mine', 'fascin', 'field', 'natur', 'languag',
                    'process', 'enthusiast', '.']
        result = self.preprocess.preprocess(self.text, lemmatize=False)
        self.assertEqual(expected, result)

    def test_preprocess_lemmatize(self):
        expected = ['text', 'mining', 'fascinating', 'field', 'natural',
                    'language', 'processing', 'enthusiast', '.']
        result = self.preprocess.preprocess(self.text)
        self.assertEqual(expected, result)
