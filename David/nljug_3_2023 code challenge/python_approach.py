from tkinter import filedialog as fd
import re
import numpy as np
class main():
    def __init__(self):
        self.file = "example_input.txt"
        self.switches = {}
        self.distances = {}
        self.paths = {}
        with open(self.file, 'r') as f:
            self.data = f.read().split('\n')
        for line in self.data:
            self.parse_line(line)
        for switch, value in self.switches.items():
            self.distances[switch] = {}
            self.calculate_base_distances(value)
        self.node_ids = self.get_node_ids()
        # for i in self.switches.keys():
        #     for j in self.switches.keys():
        #         if i != j:
        #             self.get_paths(i, j)


    def parse_line(self, line):
        letter = re.search("Switch (.) at", line)[1]
        position = re.search("at \((.*)\) is", line)[1]
        x_pos, y_pos = int(position.split(',')[0]), int(position.split(',')[1])
        connections = re.search("is connected to (.*)", line)[1]
        if connections != "nothing":
            connections = connections.split(', ')
        self.switches[letter] = {"x": x_pos,
                                 "y": y_pos,
                                 "connections": connections,
                                 "letter": letter}

    def calculate_base_distances(self, switch):
        letter = switch['letter']
        x_initial = switch['x']
        y_initial = switch['y']
        for connection in switch['connections']:
            x_target = self.switches[connection]['x']
            y_target = self.switches[connection]['y']
            distance = int(((x_target - x_initial)**2 +
                            (y_target - y_initial)**2)**0.5*100)
            self.distances[letter][connection] = distance

    def get_node_ids(self):
        return {key: i for i, key in enumerate(self.switches.keys())}

    def calculate_fastest_path(self, origin, target):
        q = np.array([])
        nodes = len(self.switches.keys())
        for node in self.distances.keys():
            paths = [np.inf for _ in range(nodes)]
            paths[self.node_ids[node]] = 0
            for connection, distance in self.distances[node].items():
                paths[self.node_ids[connection]] = distance
            q = np.append(q, paths)
        q = q.reshape(nodes, nodes)
        



    # def get_paths(self, origin, target, current= None, visited=None):
    #     if visited is None:
    #         visited = []
    #     route = origin + target
    #     if current is None:
    #         current = origin
    #         self.paths[route] = {}
    #     for connection in self.distances[current].keys():
    #         if connection == target:
    #             self.paths[route][''.join(visited + [connection])] = ''
    #         elif connection not in visited:
    #             self.get_paths(origin, target, connection, visited + [
    #                 connection])






a = main()
print('done')

