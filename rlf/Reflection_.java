package forrest.rlf;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class Reflection_ {
    public static void main(String[] args) throws Exception {

        //1. 使用Properties 类, 可以读写配置文件
        Properties properties = new Properties();//建立一个properties对象，用来读取配置文件
        properties.load(new FileInputStream("src\\re.properties"));//读取配置文件
        String classfullpath = properties.get("classfullpath").toString();//"forrest.rlf.Cat"，读取配置文件中的类路径
        String  methodName = properties.get("method").toString();//"hi"，读取配置文件中的方法名


        //2. 使用反射机制
        //(1) 加载类, 返回Class类型的对象cls
        Class cls = Class.forName(classfullpath);//通过类路径得到类的Class对象
        //(2) 通过 cls 得到你加载的类 .Cat 的对象实例
        Object o = cls.newInstance();//通过类的Class对象得到类的实例
        System.out.println("o的运行类型=" + o.getClass()); //运行类型
        //(3) 通过 cls 得到你加载的类 com.hspedu.Cat 的 methodName"hi"  的方法对象
        //    即：在反射中，可以把方法视为对象（万物皆对象）
        Method method1 = cls.getMethod(methodName);
        //(4) 通过method1 调用方法: 即通过方法对象来实现调用方法
        System.out.println("=============================");
        method1.setAccessible(true);//在反射调用方法时，取消访问检查,可以加快反射的速度
        method1.invoke(o); //传统方法 对象.方法() , 反射机制 方法.invoke(对象)

        //java.lang.reflect.Field: 代表类的成员变量, Field对象表示某个类的成员变量
        //得到name字段
        //getField不能得到私有的属性
        Field nameField = cls.getField("age"); //
        System.out.println(nameField.get(o)); // 传统写法 对象.成员变量 , 反射 :  成员变量对象.get(对象)

        //java.lang.reflect.Constructor: 代表类的构造方法, Constructor对象表示构造器
        Constructor constructor = cls.getConstructor(); //()中可以指定构造器参数类型, 返回无参构造器
        System.out.println(constructor);//Cat()



        Constructor constructor2 = cls.getConstructor(String.class); //这里传入的 String.class 就是String类的Class对象
        System.out.println(constructor2);//Cat(String name)
    }
}