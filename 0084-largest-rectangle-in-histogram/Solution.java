class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            // Cột giả cao 0 để ép pop hết stack
            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {

                int height = heights[stack.pop()];

                // Nếu stack rỗng thì kéo dài từ đầu mảng
                int left = stack.isEmpty() ? -1 : stack.peek();

                int width = i - left - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}