class Solution {
    private List<List<Integer>> graph;
    private List<List<Integer>> result;
    private int[] disc;
    private int[] low;
    private int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        result = new ArrayList<>();

        disc = new int[n];
        low = new int[n];
        time = 0;

        Arrays.fill(disc, -1);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(0, -1);

        return result;
    }

    private void dfs(int u, int parent) {
        disc[u] = low[u] = time;
        time++;

        for (int v : graph.get(u)) {
            if (v == parent) {
                continue;
            }

            if (disc[v] == -1) {
                dfs(v, u);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    result.add(Arrays.asList(u, v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}