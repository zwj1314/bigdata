import java.util.Scanner;

/**
 * Created by zhangjian 2018/03/10
 * description:
 *
 */
public class Test5 {
    public static void main(String[] args){
        int[] arr=new int[5];
        Scanner sc=new Scanner(System.in);
        for (int i=0;i<arr.length;i++){
            System.out.println("请输入一个数:");
            int num=sc.nextInt();
            arr[i]=num;
        }
        int[] newarr=new int[5];
        int index=0;
        for(int i=arr.length-1;i>=0;i--){
            int num=arr[i];
            if (num>=0){
                newarr[index]=num;
            }else {
                newarr[index]=0;
            }
            index++;

        }
        System.out.println("原数组为：");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println("新数组为：");
        for(int i=0;i<newarr.length;i++){
            System.out.println(newarr[i]);
        }

    }
}
