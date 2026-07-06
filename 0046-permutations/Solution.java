class Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums,
                           boolean[] used,
                           List<Integer> path,
                           List<List<Integer>> result) {

        // Đã tạo đủ một hoán vị
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Thử từng số
        for (int i = 0; i < nums.length; i++) {

            // Đã dùng thì bỏ qua
            if (used[i]) {
                continue;
            }

            // Chọn
            used[i] = true;
            path.add(nums[i]);

            // Đi tiếp
            backtrack(nums, used, path, result);

            // Quay lui
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}