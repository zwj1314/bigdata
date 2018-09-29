import java.util.Scanner;
/**
 * Created by zhangjian 2018/03/10
 * description:
 *
 */
public class Test4 {
    public static void main(String[] args) {
        int arr[] = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.print("请输入一个数:");
            int num = sc.nextInt();
            arr[i] = num;
        }
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                count1 += 1;
            } else if (arr[i] == 2) {
                count2 += 1;
            } else if (arr[i] == 3) {
                count3 += 1;
            } else {
                count4 += 1;
            }
        }
        System.out.println("1的个数为"+count1);
        System.out.println("2的个数为"+count2);
        System.out.println("3的个数为"+count3);
        System.out.println("不合规的个数为"+count4);
    }

}





