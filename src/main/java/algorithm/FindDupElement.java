package algorithm;


public class FindDupElement {
    public void test1() {
        int[] num = {2, 1, 3, 1, 4, 3, 4, 4, 5};
        for (int i = 0; i < num.length; i++) {
            int arg = num[i];
            for (int j = i + 1; j < num.length; j++) {
                if (arg == num[j]) {
                    System.out.println(arg);
                }
            }
        }
    }
}
