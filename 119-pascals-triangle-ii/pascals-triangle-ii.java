class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long ans = 1;
        row.add(1);

        for (int col = 1; col <= rowIndex; col++) {
            ans = ans * (rowIndex - col + 1) / col;
            row.add((int) ans);
        }

        return row;
    }
}