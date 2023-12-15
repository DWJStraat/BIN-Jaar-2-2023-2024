from collections import defaultdict
import graphviz
from itertools import *

class Graph:
    def __init__(self):
        self.edges = defaultdict(list)
        self.weights = {}

    def add_edge(self, from_node, to_node, distance):
        self.edges[from_node].append(to_node)
        self.edges[to_node].append(from_node)
        self.weights[(from_node, to_node)] = distance
        self.weights[(to_node, from_node)] = distance

    def build(self):
        self.graph = graphviz.Graph()
        for x in self.edges:
            for y in self.edges[x]:
                self.graph.edge(str(x), str(y), label=str(self.weights[(x, y)]))
        self.graph.render('test-output/round-table.gv', view=True)

    def get_val(self, from_node, to_node):
        if from_node == to_node:
            return 0
        if (to_node, from_node) not in self.weights:
            return 100
        return self.weights[(from_node, to_node)]

    def get_fitting(self, currentlist, selected, front = True):
        first = currentlist[0]
        last = currentlist[-1]
        if front:
            select_dist = self.get_val(selected, first)
        else:
            select_dist = self.get_val(selected, last)
        for x in currentlist:
            if front:
                dist = self.get_val(selected, x)
                frontdist = self.get_val(first, x)
                if dist + frontdist != select_dist:
                    return False
            else:
                dist = self.get_val(selected, x)
                backdist = self.get_val(last, x)
                if dist + backdist != select_dist:
                    return False
        return True




def dijkstra(grap, initial, end):
    shortest_paths = {initial: (None, 0)}
    current_node = initial
    visited = set()

    while current_node != end:
        visited = visited.union({current_node})
        destinations = grap.edges[current_node]
        weight_to_current_node = shortest_paths[current_node][1]

        for next_node in destinations:
            weight = grap.weights[(current_node, next_node)] + weight_to_current_node
            if next_node not in shortest_paths:
                shortest_paths[next_node] = (current_node, weight)
            else:
                current_shortest_weight = shortest_paths[next_node][1]
                if current_shortest_weight > weight:
                    shortest_paths[next_node] = (current_node, weight)
        next_destinations = {node: shortest_paths[node] for node in shortest_paths if node not in visited}
        if not next_destinations:
            return "Route Not Possible"
        current_node = min(next_destinations, key=lambda k: next_destinations[k][1])

    path = []
    while current_node is not None:
        path.append(current_node)
        next_node = shortest_paths[current_node][0]
        current_node = next_node
    path = path[::-1]
    return path

def all_subsets(list):
    return permutations(list)





