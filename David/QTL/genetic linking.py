from tkinter import filedialog
import pandas


class Gene:
    def __init__(self, path=None):
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


if __name__ == '__main__':
    gene = Gene("C:\\Users\\dstra\\Downloads\\CvixLer-MarkerSubset-LG1.txt")
    gene.read()
    gene.build_pos_list()
