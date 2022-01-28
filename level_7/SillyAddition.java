/*
    For this kata you will have to forget how to add two numbers.
    In simple terms, our method does not like the principle of carrying over numbers and just writes down every number it calculates :-)

    You may assume both integers are positive integers.

    Examples:
    16+18=    1+1 = 2,  6+8 = 14,  so result is: 214
    26+39=    2+3 = 5,  6+9 = 15,  so result is: 515
    You may assume both integers are positive integers and the result will not be bigger than Integer.MAX_VALUE
*/

public class SillyAddition {
    public static void main(String[] args) {
        System.out.println(add(2, 11));
    }

    public static int add(int num1, int num2) {
        String nb1 = String.valueOf(num1);
        String nb2 = String.valueOf(num2);
        String[] n1 = nb1.split("");
        String[] n2 = nb2.split("");
        StringBuilder result = new StringBuilder();
        int temp;
        if (n1.length == n2.length) {
            for (int i = 0; i < n1.length; i++) {
                temp =Integer.parseInt(n1[i]) + Integer.parseInt(n2[i]);
                result.append(temp);
            }
        } else if (n1.length > n2.length) {
            int diff = n1.length - n2.length;
            for (int i = 0; i < n1.length; i++) {
                if (i < diff) {
                    result.append(n1[i]);
                } else {
                    temp =Integer.parseInt(n1[i]) + Integer.parseInt(n2[i-diff]);
                    result.append(temp);
                }
            }
        } else if (n2.length > n1.length) {
            int diff = n2.length - n1.length;
            for (int i = 0; i < n2.length; i++) {
                if (i < diff) {
                    result.append(n2[i]);
                } else {
                    temp =Integer.parseInt(n1[i-diff]) + Integer.parseInt(n2[i]);
                    result.append(temp);
                }
            }
        }
        return Integer.parseInt(result.toString());
    }
}
