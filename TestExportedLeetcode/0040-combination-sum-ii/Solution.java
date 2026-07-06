class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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

        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {

            // 🚨 skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);

            backtrack(
                candidates,
                remain - candidates[i],
                i + 1,   // ❗ chỉ dùng mỗi số 1 lần
                path
            );

            path.remove(path.size() - 1);
        }
    }
}