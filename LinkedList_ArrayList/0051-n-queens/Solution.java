class Solution {

    private List<List<String>> result = new ArrayList<>();

    private Set<Integer> cols = new HashSet<>();
    private Set<Integer> diag1 = new HashSet<>(); // row - col
    private Set<Integer> diag2 = new HashSet<>(); // row + col

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];

        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, board, n);

        return result;
    }

    private void backtrack(int row, char[][] board, int n) {

        if (row == n) {

            List<String> solution = new ArrayList<>();

            for (char[] r : board) {
                solution.add(new String(r));
            }

            result.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {

            if (cols.contains(col) ||
                diag1.contains(row - col) ||
                diag2.contains(row + col)) {
                continue;
            }

            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            backtrack(row + 1, board, n);

            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }
}