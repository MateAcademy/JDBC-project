package com.itvdn.javastarter.test.lib;



import com.itvdn.javastarter.test.factory.BetDaoFactory;
import com.itvdn.javastarter.test.simple_dao.Main;
import org.reflections.Reflections;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class Injector {

    public static Object getInstanse(Class clazz) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {

        //создаю через конструктор обьект сlazz
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object instance = declaredConstructor.newInstance();

        // прохожусь по полям там где аннотация Inject
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {

                field.setAccessible(true);

                String typeField = field.getName();
                Class aClass = reflectionsScan(typeField);
                //в этом месте я должен внедрить класс нужный класс над которым аннотация дао
                //field.set(instance, BetDaoFactory.getBetDao());
                field.set(instance, BetDaoFactory.getObject(aClass));

            }
        }
        return instance;
    }

    private static Class reflectionsScan(String type) {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Dao.class);

        for(Class cls: classes) {
            String string1 = type.toLowerCase();
            String string2 = cls.getSimpleName().toLowerCase();
            if (string2.startsWith(string1))
            return cls;
        }
        return null;
    }

//    public static void injectDependency() throws ClassNotFoundException {
//        Class<ConsoleHandler> consoleHandlerClass = (Class<ConsoleHandler>) Class.forName("com.trackenshure.controller.ConsoleHandler");
//        Class<BetDaoImpl> betDaoClass = BetDaoImpl.class;
//
//        //Class<ConsoleHandler> consoleHandlerClass2 =  new ConsoleHandler().getClass();
//        //Class<ConsoleHandler> consoleHandlerClass3 =  ConsoleHandler.class;
//    }
}
