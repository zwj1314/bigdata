import java.util.Random;
/**
 * created by zj 2018/03/20
 * 1.定义长度为10的数组；
 * 2.数组中的数据为随机生成的1-100的数字
 * 3.
 *
 */
public class Test6 {
    public static void main(String[] args){
        int[] arr=new int[10];
        Random r =new Random();
        for (int i=0;i<arr.length;i++){
            arr[i]=r.nextInt(100)+1;//[0-99]+1
        }
        System.out.println("原数组为：");
        for (int i=0;i<arr.length;i++){
            System.out.print("\t"+arr[i]);
        }



    }
}
