from math import factorial
from tkinter import filedialog
import pandas
from alive_progress import alive_bar


class Gene:
    def __init__(self, path=None):
        self.permutations = []
        self.permcount = 0
        self.dataframe = None
        self.data = None
        self.nind = None
        self.nloc = None
        self.popt = None
        self.name = None
        self.timestamp = None
        if path is None:
            self.path = filedialog.askopenfilename()
        else:
            self.path = path

    def read(self):
        with open(self.path) as f:
            contents = f.read()
            self.timestamp = contents.split('\n')[0]
            self.name = contents.split('\n')[2]
            self.popt = contents.split('\n')[3]
            self.nloc = contents.split('\n')[4]
            self.nind = contents.split('\n')[5]
            filecontents = contents.split('\n')[7:]
            output = {}
            characters = ''
            for line in filecontents:
                if not line.startswith(' ') and not characters == '':
                    output[marker] = characters.strip().split(' ')
                    marker = line
                    characters = ''
                elif not line.startswith(' ') and characters == '':
                    marker = line
                else:
                    characters += line.replace('  ', ' ')
            self.data = output
            self.dataframe = pandas.DataFrame(output)

    def build_pos_list(self):
        self.pos_list = {}
        for marker in self.data:
            chars = self.data[marker]
            freq = {}
            counter = 0
            for char in chars:
                if char in freq:
                    freq[char] += 1
                else:
                    freq[char] = 1
                counter += 1
            freq['total'] = counter
            self.pos_list[marker] = {
                'chars': chars,
                'freq': freq
            }

    def permutation_change_calc(self):
        permutations = []
        markers = list(self.data.keys())
        self.possible_permutations = 380
        self.permutation_builder(markers)
        return permutations

    def permutation_builder(self, marker_list, marker_index=0,
                            result=''):
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


if __name__ == '__main__':
    gene = Gene("CvixLer-MarkerSubset-LG1.txt")
    gene.read()
    gene.build_pos_list()
    gene.permutation_change_calc()
