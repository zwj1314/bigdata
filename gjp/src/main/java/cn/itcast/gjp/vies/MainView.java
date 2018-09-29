package cn.itcast.gjp.vies;

import cn.itcast.gjp.contrller.ZhangWuController;
import cn.itcast.gjp.domain.ZhangWu;

import java.util.List;
import java.util.Scanner;


/**
 *视图层，用户看到和操作的界面
 *数据传递给控制层
 *成员位置,创建controller对象
 *
 */
public class MainView {

    private ZhangWuController controller = new ZhangWuController();

    /*
     *  实现界面效果
     *  接收用户的输入
     *  根据数据,调用不同的功能方法
     */
    public void run() {
        while (true){
            System.out.println("---------------管家婆家庭记账软件---------------");
            System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　5.退出系统");
            System.out.println("请输入要操作的功能序号[1-5]:");
            Scanner sc = new Scanner(System.in);
            //接收用户的菜单选择
            int flag = sc.nextInt();
            //对选择的菜单进行判断，调用不同的功能
            switch (flag){
                case 1:
                    //调用添加账务的方法
                    addZhangWu();
                    break;

                case 2:
                    //调用编辑账务的方法
                    editZhangWu();
                    break;

                case 3:
                    //调用删除账务的方法
                    deleteZhangWu();
                    break;

                case 4:
                    //调用查询账务的方法
                    selectZhangWu();
                    break;

                case 5:
                    //退出系统
                    System.exit(0);
                    break;
            }



        }





    }


    /*
     * 定义方法addZhangWu
     * 添加账务的方法，用户在界面中选择菜单1的时候调用
     * 实现思想：
     * 	  接收键盘输入，5项输入，调用controller层方法
     */
    private void addZhangWu() {

        System.out.println("选择的是添加账务功能，请输入以下内容");
        Scanner sc = new Scanner(System.in);
        System.out.println("输入分类名称");
        String flname = sc.next();
        System.out.println("输入金额");
        double money = sc.nextDouble();
        System.out.println("输入账户");
        String zhanghu = sc.next();
        System.out.println("输入日期：格式XXXX-XX-xx");
        String createtime = sc.next();
        System.out.println("输入具体描述");
        String description = sc.next();

        //将接收到的所有用户输入的信息封装成ZhangWu对象
        ZhangWu zw= new ZhangWu(0,flname,money,zhanghu,createtime,description);

        //调用控制层control的方法addZhangWu()
        controller.addZhangWu(zw);
        System.out.println("恭喜,添加账务成功");

    }




    /*
    定义一个函数editZhangWu，实现编辑功能
    接收用户输入账务信息并封装成ZhangWu对象
    将此对象传递给controller层的editZhangWu方法

     */
    private void editZhangWu(){

        //先将所有的查询结果展示给用户
        selectAll();
        System.out.println("请输入要编辑的序号：");
        Scanner sc = new Scanner(System.in);
        int zwid = sc.nextInt();
        System.out.println("输入分类名称");
        String flname = sc.next();
        System.out.println("输入金额");
        double money = sc.nextDouble();
        System.out.println("输入账户");
        String zhanghu = sc.next();
        System.out.println("输入日期：格式XXXX-XX-xx");
        String createtime = sc.next();
        System.out.println("输入具体描述");
        String description = sc.next();

        //将所有接收到的数据封装成一个账务对象
        ZhangWu zw = new ZhangWu(zwid,flname,money,zhanghu,createtime,description);

        //调用controller层的editZhangWu方法，将账务对象zw传递给editZhangWu
        controller.editZhangWu(zw);

        System.out.println("编辑账务信息成功！");


    }


    /*
    定义方法，实现删除用户的账务信息，根据zwid来删除信息
    接收用户的输入信息，作为service层的参数，调用service的deleteZhangWu方法
     */
    private void deleteZhangWu() {
        selectAll();
        System.out.println("选择的是删除功能，请输入序号即可");
        Scanner sc = new Scanner(System.in);
        int zwid = sc.nextInt();
        controller.deleteZhangWu(zwid);
        System.out.println("删除账务成功");

    }


    /*
     * 定义方法 selectZhangWu()
     * 显示查询的方式 1 所有查询   2 条件查询
     * 接收用户的选择
     */
    private void selectZhangWu() {
        System.out.println("请输入要查询的方式：1.查询所有    2.条件查询");
        Scanner sc = new Scanner(System.in);
        int flag = sc.nextInt();
        switch (flag){
            case 1:
                selectAll();
                break;

            case 2:
                select();
                break;
        }



    }
    /*
     * 定义方法,实现条件查询账务数据
     * 提供用户的输入日期,开始日期结束日期
     * 就2个日期,传递到controller层
     * 调用controller的方法,传递2个日期参数
     * 获取到controller查询的结果集,打印出来
     */
    private void select() {
        System.out.println("选择条件查询,输入日期格式XXXX-XX-XX");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入开始日期:");
        String startDate = sc.nextLine();
        System.out.print("请输入结果日期:");
        String endDate = sc.nextLine();
        //调用controller层的方法,传递日期,获取查询结果集
        List<ZhangWu> list =controller.select(startDate,endDate);
        if (list.size()!=0){
            printinfo(list);

        }else {
            System.out.println("没有查询到数据");
        }

    }

    /*
     * 定义方法selectAll()
     * 查询所有账务的方法，用户在界面中查询菜单界面选择1的时候调用
     * 实现思想：
     * 	  查询所有账务的信息，调用controller层方法
     */
    private void selectAll() {
        //定义一个数组对象，接收controller层返回的信息
        List<ZhangWu> list = controller.selectAll();
        if (list.size()!=0){
            printinfo(list);
        }else {
            System.out.println("没有查询到数据");
        }
    }

    //显示打印所有账务信息
    private void printinfo(List<ZhangWu> list) {
        //输出表头
        System.out.println("ID\t\t类别\t\t账户\t\t金额\t\t时间\t\t说明");
        //遍历集合,结果输出控制台
        for (ZhangWu zw:
             list) {
            System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t\t"+zw.getZhanghu()+"\t\t"+
                    zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t"+zw.getDecsription());

        }

    }
}
