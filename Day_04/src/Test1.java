public class Test1 {
    public static void main(String[] args){
        int[] arr=new int[5];
        //int[] arr={1,3,10,-1,6}
        //int[] arr=new int[]{1,2,3}
        arr[0]=3;
        arr[1]=5;
        arr[2]=-1;
        arr[3]=8;
        arr[4]=10;
        int min=arr[0];
        for (int i=0;i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        System.out.println("最小值:"+min);


    }
}
