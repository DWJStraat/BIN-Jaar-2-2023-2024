from tkinter import filedialog
import pandas

class Gene:
    def __init__(self, path = None):
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


if __name__ == '__main__':
    gene = Gene()
    gene.read()

