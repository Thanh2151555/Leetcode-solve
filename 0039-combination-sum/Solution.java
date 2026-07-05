class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] candidates,
                           int remain,
                           int start,
                           List<Integer> path) {

        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (remain < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            path.add(candidates[i]);

            backtrack(
                candidates,
                remain - candidates[i],
                i,              // dùng lại được
                path
            );

            path.remove(path.size() - 1);
        }
    }
}