package coinadjacencycount;

public class Solution {

	public static void main(String[] args )
	{
		Solution s = new Solution();
		System.out.println(s.solution(new int[]{1,1,1}));
	}
	
	/*
	 * need to find and fix the bug in the following code. The instruction say that I should have to fix
	 * at the most 3 lines to fix the bug
	 */
	int solution(int[] A ) 
	{
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
//              r = r + count;
        }
        return result + r;
    
	}
}
