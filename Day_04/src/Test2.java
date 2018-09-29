import java.util.Random;
public class Test2 {
    public static void main(String[] args){
        int[] arr=new int[3];
        //int[] arr={1,3,5};
        Random r=new Random();
        for (int i=0;i<arr.length;i++) {
            arr[i] = r.nextInt(10) + 1;
            System.out.println(arr[i]);
        }


    }
}
