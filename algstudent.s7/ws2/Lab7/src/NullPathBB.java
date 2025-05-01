import java.util.*;
import labs.examples.branchandbound.pyramid.utils.BranchAndBound;
import labs.examples.branchandbound.pyramid.utils.Heap;
import labs.examples.branchandbound.pyramid.utils.Node;

public class NullPathBB extends BranchAndBound {
    static final int MIN_WEIGHT = 10;
    static final int MAX_WEIGHT = 99;
    static final int TARGET_COST = 0;
    static final int TOLERANCE = 20;
    static final double p1 = 0.5;
    static final double p2 = 1 - p1;
    static int[][] graph;
    static int n = 5;

    static int[][] generateRandomGraph() {
        graph = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    boolean positive = rand.nextInt(101) < p1 * 100;
                    int weight = rand.nextInt(MIN_WEIGHT, MAX_WEIGHT + 1);
                    graph[i][j] = positive ? weight : -weight;
                }
            }
        }
        return graph;
    }
    
    public void printMatrix() {
        for (int[] row : graph) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
    }

    public static class PathNode extends Node {
        ArrayList<Integer> path;
        int cost;

        public PathNode(ArrayList<Integer> path, int cost, int depth, UUID parentID) {
            this.path = new ArrayList<>(path);
            this.cost = cost;
            this.depth = depth;
            this.parentID = parentID;
            this.ID = UUID.randomUUID();
            calculateHeuristicValue();
        }

        @Override
        public void calculateHeuristicValue() {
            int last = path.get(path.size() - 1);
            int bestDelta = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!path.contains(i)) {
                    int candidateCost = cost + graph[last][i];
                    int delta = Math.abs(candidateCost - TARGET_COST);
                    if (delta < bestDelta) {
                        bestDelta = delta;
                    }
                }
            }

            if (bestDelta == Integer.MAX_VALUE) {
                this.heuristicValue = Math.abs(cost - TARGET_COST);
            } else {
                this.heuristicValue = bestDelta;
            }
        }

        @Override
        public ArrayList<Node> expand() {
            ArrayList<Node> children = new ArrayList<>();
            int last = path.get(path.size() - 1);

            for (int i = 0; i < n; i++) {
                if (!path.contains(i)) {
                    int edgeWeight = graph[last][i];
                    int newCost = cost + edgeWeight;
                    ArrayList<Integer> newPath = new ArrayList<>(path);
                    newPath.add(i);
                    PathNode child = new PathNode(newPath, newCost, depth + 1, this.ID);
                    children.add(child);
                }
            }

            children.sort(Comparator.comparingInt(n -> {
                PathNode pn = (PathNode) n;
                return Math.abs(pn.cost - TARGET_COST);
            }));

            return children;
        }

        @Override
        public boolean isSolution() {
            return path.size() == n && Math.abs(cost - TARGET_COST) <= TOLERANCE;
        }

        @Override
        public String toString() {
            return "Path: " + path.toString() + " | Cost: " + cost;
        }
    }

    public static void main(String[] args) {
        generateRandomGraph();
        NullPathBB solver = new NullPathBB();
        ArrayList<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);
        PathNode start = new PathNode(initialPath, 0, 0, null);
        solver.printMatrix();
        solver.rootNode = start;
        solver.branchAndBound(start);
        solver.printSolutionTrace();
    }
}

