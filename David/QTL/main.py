from genetic_linking import *
from dijkstra import *
from tsp import *
import numpy as np
from python_tsp.exact import solve_tsp_dynamic_programming
import pandas as pd
import openpyxl

if __name__ == '__main__':
    gene = Gene("CvixLer-MarkerSubset-LG1.txt")
    gene.read()
    gene.score()
    a = gene.build_dijkstra_matrix()
    matrix = []
    for key in gene.point_matrix:
        matrix.append(gene.point_matrix[key])
    matrix = np.array(matrix)
    matrix[:, 0] = 0
    permutation, distance = solve_tsp_dynamic_programming(matrix)



