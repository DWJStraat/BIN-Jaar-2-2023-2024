from ortools.sat.python import cp_model

def tsp(distance_matrix):
    """
    Solves the traveling salesman problem, utilizing Google's OR-Tools.
    Code adapted from
    https://github.com/google/or-tools/blob/stable/examples/python/tsp_sat.py
    :param distance_matrix:
    :return:
    """
    num_nodes = len(distance_matrix)
    all_nodes = range(num_nodes)

    model = cp_model.CpModel()

    variables =[]
    coefficients = []

    arcs = []
    literal_arcs = []
    for i in all_nodes:
        for j in all_nodes:
            if i == j:
                continue
            literal = model.NewBoolVar('x[%i,%i]' % (i, j))
            arcs.append((i, j, literal))
            literal_arcs[i][j] = literal

            variables.append(literal)
            coefficients.append(distance_matrix[i][j])

    model.AddCircuit(arcs)

    model.Minimize(sum(variables[i] * coefficients[i] for i in range(len(
        variables))))

    solver = cp_model.CpSolver()
    solver.parameters.linearization_level = 2

    solver.Solve(model)

    current_node = 0
    str_node = f'{current_node}'
    finished = False
    distance = 0
    while not finished:
        for i in all_nodes:
            if i == current_node:
                continue
            if solver.BooleanValue(literal_arcs[current_node, i]):
                str_node += f' -> {i}'
                current_node = i
                distance += distance_matrix[current_node][i]
                break
        if current_node == 0:
            finished = True

    return str_node, distance



