import json

from helper import draw_coloured_map, generate_graph_map


if __name__ == "__main__":
    n = 4
    map = generate_graph_map(n)
    solution = greedy(map["graph"])

    if solution:
        print("Solution found:", solution)
        draw_coloured_map(map, solution)
        with open('solution.json', 'w') as f:
            json.dump(solution, f)
            f.close()
    else:
        print("Solution not found.")
