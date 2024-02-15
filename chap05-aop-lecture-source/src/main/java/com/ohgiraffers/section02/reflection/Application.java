package com.ohgiraffers.section02.reflection;

import java.lang.reflect.*;

public class Application {
    public static void main(String[] args) {

        /* 설명. 1. Class 타입의 Class 메타정보 추출 */
        Class class1 = Account.class;
        System.out.println("class1 = " + class1);

        Class class2 = new Account().getClass();
        System.out.println("class2 = " + class2);

        try {
            Class class3 = Class.forName("com.ohgiraffers.section02.reflection.Account");
            System.out.println("class3 = " + class3);

            Class class4 = Class.forName("[D");
            Class class5 = double[].class;
            System.out.println("class4 = " + class4);
            System.out.println("class5 = " + class5);

            Class class6 = Class.forName("[Ljava.lang.String;");
            Class class7 = String[].class;
            System.out.println("class6 = " + class6);
            System.out.println("class7 = " + class7);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 2. 필드 정보 추출 */
        Field[] fields = Account.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("modifier : " + Modifier.toString(field.getModifiers())
                            + ", type : " + field.getType()
                            + ", name : " + field.getName());
        }

        /* 설명. 3. 생성자 정보 추출 */
        Constructor[] constructors = Account.class.getConstructors();       // 생성자들이 배열로 담김
        for (Constructor constructor : constructors) {
            System.out.println("name : " + constructor.getName());

            Class[] params = constructor.getParameterTypes();
            for (Class param : params) {
                System.out.println("paramType : " + param.getTypeName());
            }
        }

        try {
            Account acc = (Account) constructors[0].newInstance("20", "110-123-12345", "1234", 10000);
            System.out.println(acc.getBalance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 4. 메소드 정보 추출 */
        Method[] methods = Account.class.getMethods();
        Method getBalanceMethod = null;
        for (Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " "
                                + method.getReturnType().getSimpleName() + " "
                                + method.getName());

            if("getBalance".equals(method.getName())) {
                getBalanceMethod = method;
            }
        }

        try {
            System.out.println(getBalanceMethod.invoke((Account)constructors[2].newInstance()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
