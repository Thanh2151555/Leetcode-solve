class Solution {

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        // Thử bắt đầu từ mọi ô
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dfs(board, word, i, j, 0)) {
                    return true;
                }

            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {

        // Đã tìm hết word
        if (index == word.length()) {
            return true;
        }

        // Ra ngoài bảng
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length) {
            return false;
        }

        // Ký tự không khớp
        if (board[row][col] != word.charAt(index)) {
            return false;
        }

        // Đánh dấu ô đã dùng
        char temp = board[row][col];
        board[row][col] = '#';

        // Thử 4 hướng
        boolean found =
                dfs(board, word, row - 1, col, index + 1) || // lên
                dfs(board, word, row + 1, col, index + 1) || // xuống
                dfs(board, word, row, col - 1, index + 1) || // trái
                dfs(board, word, row, col + 1, index + 1);   // phải

        // Backtracking: khôi phục ô
        board[row][col] = temp;

        return found;
    }
}