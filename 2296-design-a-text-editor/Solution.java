class TextEditor {

    private StringBuilder left;
    private StringBuilder right;

    public TextEditor() {
        left = new StringBuilder();
        right = new StringBuilder();
    }
    
    public void addText(String text) {
        left.append(text);
    }
    
    public int deleteText(int k) {
        int deleteCount = Math.min(k, left.length());
        left.delete(left.length() - deleteCount, left.length());
        return deleteCount; 
    }
    
    public String cursorLeft(int k) {
        int move = Math.min(k, left.length());

        for (int i = 0; i < move; i++) {
            char c = left.charAt(left.length() - 1);
            left.deleteCharAt(left.length() - 1);
            right.append(c);
        }

        return getLeftText();
    }
    
    public String cursorRight(int k) {
        int move = Math.min(k, right.length());

        for (int i = 0; i < move; i++) {
            char c = right.charAt(right.length() - 1);
            right.deleteCharAt(right.length() - 1);
            left.append(c);
        }

        return getLeftText();
    }

    private String getLeftText() {
        int len = left.length();
        int start = Math.max(0, len - 10);
        return left.substring(start, len);
    }
}