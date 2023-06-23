package forrest.rlf;

import java.lang.reflect.Field;

public class Class1 {
    public static void main(String[] args) throws Exception {
        Class cls1 = Class.forName("forrest.rlf.Cat");//通过反射获取类的Class类对象

        Cat cat = new Cat();
        System.out.println(cat.hashCode());
        //对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
        Class cls2 = Class.forName("forrest.rlf.Cat");
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        Class cls3 = Class.forName("forrest.rlf.Dog");
        System.out.println(cls3.hashCode());


        String classAllPath = "forrest.rlf.Car";
        //1 . 获取到Car类 对应的 Class对象
        //<?> 表示不确定的Java类型
        Class<?> cls = Class.forName(classAllPath);
        //2. 输出cls
        System.out.println("cls=" + cls); //显示cls对象, 是哪个类的Class对象
        System.out.println("cls.getclass = " + cls.getClass());//输出cls运行类型 java.lang.Class
        //3. 得到包名
        System.out.println(cls.getPackage().getName());//包名
        //4. 得到全类名
        System.out.println(cls.getName());
        //5. 通过cls创建对象实例
        Car car = (Car) cls.newInstance();
        System.out.println(car);//car.toString()
        //6. 通过反射获取属性 brand
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));//宝马
        //7. 通过反射给属性赋值
        brand.set(car, "奔驰");
        System.out.println(brand.get(car));//奔驰
        //8 我希望大家可以得到所有的属性(字段)
        System.out.println("=======所有的字段属性====");
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            System.out.println(f.getName());//名称
        }

    }
}
