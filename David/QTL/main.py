"""
Runs the genetic algorithm to find the best alignment
Author: David Straat
"""

from genetic_linking import *
import numpy as np
from python_tsp.exact import solve_tsp_dynamic_programming

if __name__ == '__main__':
    path = filedialog.askopenfilename()
    try:
        gene = Gene(path)
    except FileNotFoundError:
        print("File not found")
        exit()
    gene.read()
    gene.score()
    matrix = []
    for key in gene.point_matrix:
        matrix.append(gene.point_matrix[key])
    matrix = np.array(matrix)
    matrix[:, 0] = 0
    permutation, distance = solve_tsp_dynamic_programming(matrix)
    dist_mod = distance / 100
    name_0 = gene.marker_names[0]
    pos_list = {name_0: 0}
    for i in range(1, len(permutation)):
        previous = permutation[i - 1]
        previous_name = gene.marker_names[previous]
        current = permutation[i]
        current_name = gene.marker_names[current]
        distance = gene.point_matrix[previous_name][current]
        pos_list[current_name] = pos_list[previous_name] + distance / dist_mod

    output_list = ["Group 1"]
    for key in pos_list:
        name = key.split(" ")[0]
        output_list.append(f"{name}\t{pos_list[key]}")
    output = "\n".join(output_list)
    with open("output.txt", "w") as f:
        f.write(output)
