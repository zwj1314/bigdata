package cn.itheima.controller;

import cn.itheima.pojo.Items;
import cn.itheima.service.ItemsService;
import cn.itheima.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 */
@Controller
//@RequestMapping("/items") 窄化请求路径  items/list.action 防止重名冲突
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/list")
    public ModelAndView itemsList() {
        List<Items> list = itemsService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", list);
        modelAndView.setViewName("itemList");
        return modelAndView;
    }

    /**
     * springMvc中默认的参数类型，在controller方法中可以加入也可以不加
     * HttpServletRequest
     * HttpServletReponse
     * HttpSession
     * Model
     */
    @RequestMapping("/itemEdit")
    public String itemEdit(HttpServletRequest request, Model model) {
        String idStr = request.getParameter("id");
        Items items = itemsService.findItemsById(Integer.parseInt(idStr));

        //model模型：模型中放入返回给页面的数据
        model.addAttribute("item", items);

        //如果spring mvc方法返回的是一个简单的string字符串，那么springmvc会认为这个字符串就是页面的名称
        return "editItem";
    }

    //springMvc可以直接接收基本的数据类型，包括string。springMvc可以直接进行类型的转换
    //controller方法接收的参数的变量名称必须是页面上input框的name属性值
   /* @RequestMapping("/updateitem")
    public String update(Integer id, String name, Float price, String detail){
        Items items = new Items();
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        items.setDetail(detail);
        items.setCreatetime(new Date());

        itemsService.updateItems(items);

        return "success";


    }*/

    //springMvc可以直接接收pojo类型，要求页面上的input框的name属性名称必须等于pojo的属性名称
    @RequestMapping("/updateitem")
    public String update(MultipartFile pictureFile, Items items, Model model) throws IOException {
        //1.获取图片完整名称
        String fileStr = pictureFile.getOriginalFilename();
        //2.使用随机生成的字符串+原图片扩展名组成新的图片名称，防止图片重名
        String newfileName = UUID.randomUUID().toString() + fileStr.substring(fileStr.lastIndexOf("."));
        //3.将图片保存到硬盘
        pictureFile.transferTo(new File("/Users/zhangjian/Desktop/" + newfileName));
        //4.图片保存到数据库
        items.setPic(newfileName);

        //items.setCreatetime(new Date());

        itemsService.updateItems(items);
        model.addAttribute("id", items.getId());
        //请求转发：浏览器中的url不发生改变，request域中的数据可以带到重定向后的方法中
        //重定向：浏览器中的url发生改变，request域中的数据不可以带到重定向后的方法中
        return "success";

    }

  /*  @RequestMapping("/search")
    public String search(QueryVo vo) {
        System.out.println(vo);
        return "";
    }*/


    @RequestMapping("/delAll")
    public String delAll(QueryVo vo) {
        System.out.println(vo);
        return "";
    }

    @RequestMapping("updateAll")
    public String updateAll(QueryVo vo) {
        return "";
    }

    //导入jackson的jar包在controller的方法中可以使用@RequestBody，让springMvc将json格式字符串自动转换成java中的pojo
    //页面中json的key要等于java中pojo的属性名称
    //controller方法返回pojo类型的对象并且用@ResponseBody注解，springMvc会自动将pojo对象转换成json格式字符串
    @RequestMapping("/sendJson")
    @ResponseBody
    public Items json(@RequestBody Items items) {
        System.out.println(items);
        return items;
    }
}
