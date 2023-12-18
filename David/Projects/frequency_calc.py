
def frequency_calc(data):
    """
    Function to calculate the frequency of each character at each position
    :param data:
    :return:
    """
    permutations = {}
    gene_list = []
    for entry in data:
        gene_list.append(data[entry]['chars'])
    for x in range(len(gene_list[0])):
        permutations[x] = {}
        for y in range(len(gene_list)):
            char = gene_list[y][x]
            if char == '-':
                continue
            if char in permutations[x]:
                permutations[x][char] += 1
            else:
                permutations[x][char] = 1
    return permutations

def frequency_builder(permutations):
    frequencies = {}
    for marker in permutations:
        char_count = {}
        total_chars = 0
        frequencies[marker] = {}
        for char in permutations[marker]:
            char_count[char] = permutations[marker][char]
            total_chars += permutations[marker][char]
        for char in char_count:
            frequencies[marker][char] = char_count[char] / total_chars
    return frequencies

def calculate_permutation_change(permutations, gene_list):
    output_list = {}
    for gene in gene_list:
        output_list[gene] = {}
        chars = gene_list[gene]['chars']
        for char_id in range(len(chars)):
            char = chars[char_id]
            if char == '-':
                continue
            output_list[gene][char_id] = permutations[char_id][char]
    return output_list

def calc_char_frequency(gene_list):
    frequency = {}
    for gene in gene_list:
        frequency[gene] = {}
        chars = gene_list[gene]['chars']
        for char in chars:
            if char == '-':
                continue
            if char in frequency[gene]:
                frequency[gene][char] += 1
            else:
                frequency[gene][char] = 1
    return frequency

def chi_square_test(frequency_list, gene_list, threshold = 5):
    for gene in frequency_list:
        total = 0
        chars = 0
        for char in frequency_list[gene]:
            total += frequency_list[gene][char]
            chars += 1
        for char in frequency_list[gene]:
            expected = total / chars
            reality = frequency_list[gene][char]




