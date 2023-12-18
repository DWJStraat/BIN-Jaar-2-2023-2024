"""
This file contains the code for the genetic linking algorithm
for the genetic linking project. This algorithm is designed to
take in a file containing genetic data and output a file
containing the genetic data with the genetic linkage
information appended to it.
Author: David van Straat
"""

from tkinter import filedialog
import pandas


class Gene:
    """
    Class to represent a gene
    :param path: path to the file containing the gene data
    """

    def __init__(self, path=None):
        """
        Constructor for the Gene class
        :param path: path to the file containing the gene data
        """
        self.pos_list = None
        self.possible_permutations = None
        self.permutations = []  # Create an empty list
        self.permutation_count = 0  # Create a variable to keep track of the
        # number of permutations
        self.dataframe = None
        self.data = None
        self.nind = None
        self.nloc = None
        self.popt = None
        self.name = None
        self.timestamp = None
        self.point_matrix = {}
        self.marker_names = []
        if path is None:  # If no path is given
            self.path = filedialog.askopenfilename()  # Ask the user for a path
        else:  # If a path is given
            self.path = path  # Set the path variable to the given path

    def score(self):
        """
        Function to score the genes
        """
        for gene_1 in self.data:
            self.marker_names.append(gene_1)
            self.point_matrix[gene_1] = []
            for gene_2 in self.data:
                if gene_1 == gene_2:
                    pass
                self.score_2_genes(gene_1, gene_2)

    def score_2_genes(self, gene_1, gene_2):
        """"
        Build a matrix of the percentage of matching characters
        between two genes
        :param gene_1: the first gene
        :param gene_2: the second gene
        """
        val_1 = self.data[gene_1]
        val_2 = self.data[gene_2]
        counter = 0
        min_len = min(len(val_1), len(val_2))
        length = min_len
        for char in range(min_len):
            char_1 = val_1[char]
            char_2 = val_2[char]
            if char_1 == '-' or char_2 == '-':
                length -= 1
                continue
            if char_1 != char_2:
                counter += 1
        percentage = counter / length * 100
        if percentage < 50:
            self.point_matrix[gene_1].append(percentage)
        else:
            self.point_matrix[gene_1].append(0)

    def build_dijkstra_matrix(self):
        """
        Function to build the matrix for a Dijkstra algorithm
        """
        dijkstra_matrix = []
        for gene_1 in self.point_matrix:
            for value in self.point_matrix[gene_1]:
                index = self.point_matrix[gene_1].index(value)

                gene_2 = self.marker_names[index]
                if (gene_1 == gene_2 or (gene_2, gene_1, value) in
                    dijkstra_matrix) or value == 0:
                    continue
                dijkstra_matrix.append((gene_1, gene_2, value))
        return dijkstra_matrix

    def read(self):
        """
        Function to read the data from the file
        :return: None
        """
        with open(self.path) as f:
            contents = f.read()  # Read the file
            self.timestamp = contents.split('\n')[0]  # Get the timestamp
            self.name = contents.split('\n')[2]  # Get the name of the marker
            self.popt = contents.split('\n')[3]  # Get the population
            self.nloc = contents.split('\n')[4]  # Get the number of loci
            self.nind = contents.split('\n')[
                5]  # Get the number of individuals
            file_contents = contents.split('\n')[7:]  # Get the actual data
            output = {}
            characters = ''
            for line in file_contents:  # Loop through the data
                if not line.startswith(' ') and not characters == '':
                    # If the line doesn't start with a space and the
                    # characters variable is not empty
                    output[marker] = characters.strip().split(
                        ' ')  # Add the characters to the output dictionary
                    marker = line  # Set the marker variable to
                    # the current line

                    characters = ''  # Reset the characters variable
                elif not line.startswith(' ') and characters == '':  # If the
                    # line doesn't start with a space and the characters
                    # variable is empty
                    marker = line  # Set the marker variable to the current
                    # line

                else:
                    characters += line.replace('  ',
                                               ' ')  # Add the
                    # characters to the characters variable

            self.data = output  # Set the data variable to the output
            # dictionary
            self.dataframe = pandas.DataFrame(
                output)  # Set the dataframe variable to the output dictionary

    def build_pos_list(self):
        """
        Function to build a list of positions
        :return: None
        """
        self.pos_list = {}  # Create an empty dictionary
        for marker in self.data:  # Loop through the markers
            chars = self.data[
                marker]  # Get the characters for the current marker
            freq = {}  # Create an empty dictionary
            counter = 0  # Create a counter variable
            for char in chars:  # Loop through the characters
                if char in freq:  # If the character is already in the
                    # dictionary
                    freq[char] += 1  # Add one to the value of the character
                else:  # If the character is not in the dictionary
                    freq[char] = 1  # Set the value of the character to 1
                counter += 1  # Add one to the counter
            freq['total'] = counter  # Set the total value to the counter
            self.pos_list[marker] = {  # Add the marker to the dictionary
                'chars': chars,  # Add the characters to the dictionary
                'freq': freq  # Add the frequencies to the dictionary
            }

    def permutation_change_calc(self):
        """
        Function to calculate the change in permutation
        :return: the different permutations
        """
        permutations = []
        markers = list(self.data.keys())
        self.possible_permutations = 380
        self.permutation_builder(markers)
        return permutations

    def permutation_builder(self, marker_list, marker_index=0,
                            result=''):
        """
        Function to build the permutations
        :param marker_list: A list of markers
        :param marker_index: A list of indices for the markers
        :param result: The current result. Defaults to an empty string
        """
        try:
            marker = marker_list[marker_index]
            chars = self.pos_list[marker]['freq'].keys()
            for char in chars:
                if char == 'total':
                    continue
                self.permutation_builder(marker_list, marker_index + 1,
                                         result + char)
        except IndexError:
            self.permutations.append(result)

