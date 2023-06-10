package indi.mofan.whitebox;

import indi.mofan.entity.Animal;
import indi.mofan.entity.Cat;
import indi.mofan.entity.PrivateConstructor;
import indi.mofan.entity.Student;
import indi.mofan.entity.TestClass;
import indi.mofan.entity.University;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author mofan
 * @date 2021/4/12 14:18
 */
public class WhiteBoxTest {

    @Test
    public void testGetInternalState() {
        ServiceHolder holder = new ServiceHolder();
        final Object obj = new Object();

        holder.addService(obj);

        // 方式一：获取 holder 中 objectSet 属性值
        Set<String> objectSet = Whitebox.getInternalState(holder, "objectSet");
        assertEquals(1, objectSet.size());
        assertSame(obj, objectSet.iterator().next());

        // 方式二：在 PowerMock 1.0 及其以上的版本，还可以这样获取属性值
        Set<String> set = Whitebox.getInternalState(holder, Set.class);
        assertEquals(1, set.size());
        assertSame(obj, set.iterator().next());
    }

    @Test
    public void testSetInternalState() {
        Animal animal = new Animal();
        // 方式一：为 animal 中的 name 属性设置值
        Whitebox.setInternalState(animal, "name", "name");
        assertEquals("name", animal.getName());

        // 方式二：在 PowerMock 1.0 及其以上的版本，还可以这样设置属性值
        Whitebox.setInternalState(animal, "TestName");
        assertEquals("TestName", animal.getName());
    }

    @Test
    public void testFieldWithTheSameName() {
        Cat cat = new Cat();
        Whitebox.setInternalState(cat, "name", "nameOfAnimal", Animal.class);
        /*
         * 第二种赋值的方式：
         * Whitebox.setInternalState(cat, String.class, "nameOfAnimal", Animal.class);
         */
        String name1 = Whitebox.<String>getInternalState(cat, "name", Animal.class);
        String name2 = Whitebox.getInternalState(cat, String.class, Animal.class);
        assertEquals("nameOfAnimal", name1);
        assertEquals("nameOfAnimal", name2);
        assertEquals("nameOfAnimal", cat.getSuperName());
        assertNull(cat.getName());

        Whitebox.setInternalState(cat, "TestName");
        String name = Whitebox.getInternalState(cat, "name");
        assertEquals("TestName", name);
        assertEquals("TestName", cat.getName());
    }

    @Test
    public void testInvokeMethod() {
        InvokeMethod method = new InvokeMethod();
        try {
            int sum1 = Whitebox.invokeMethod(method, "sum", 1, 2);
            assertEquals(3, sum1);
            int result1 = Whitebox.invokeMethod(method, "method", new Class<?>[]{int.class}, 2);
            assertEquals(4, result1);
            int result2 = Whitebox.invokeMethod(method, "method", new Class<?>[]{Integer.class}, 3);
            assertEquals(9, result2);
            int product = Whitebox.invokeMethod(InvokeMethod.class, "multiplyMethod", 2, 3);
            assertEquals(6, product);
            double diff = Whitebox.invokeMethod(method, 6.5, 3.1);
            assertEquals(3.4, diff, 0.0);
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testPrivateConstructor() {
        try {
            PrivateConstructor constructor = Whitebox.invokeConstructor(PrivateConstructor.class, "Test");
            assertEquals("Test", constructor.getCode());

            PrivateConstructor privateConstructor1 =
                    Whitebox.invokeConstructor(PrivateConstructor.class, new Class<?>[]{int.class},
                            Collections.singletonList(5).toArray());
            assertEquals(5, privateConstructor1.getState());
            PrivateConstructor privateConstructor2 =
                    Whitebox.invokeConstructor(PrivateConstructor.class, new Class<?>[]{Integer.class},
                            Collections.singletonList(6).toArray());
            assertEquals(6, privateConstructor2.getState());
        } catch (Exception e) {
            fail();
        }
    }


    /**
     * 在 Student 类中有一个 University 类型的私有成员变量 school，它在构造方法中被初始化
     * 并且在 Student 中并没有提供与 school 相关的 Setter 方法
     * 如果想要修改 school 中 universityName 的值，可以使用 Whitebox 完成
     */
    @Test
    public void testInternalState() {
        Student student = new Student("mofan", "gender", 19);
        assertEquals("xxx大学", student.getSchool().getUniversityName());

        University school = Whitebox.<University>getInternalState(student, "school");
        Whitebox.setInternalState(school, "universityName", "TestUniversityName");
        assertEquals("TestUniversityName", student.getSchool().getUniversityName());
    }

    /**
     * 测试 InnerTestClass 中的 run 方法
     * 由于 InnerTestClass 是一个 private 的内部类，因此是没办法像下面这样 mock，因为这个内部类是不可见的：
     * `TestClass.InnerTestClass clazz = mock(TestClass.InnerTestClass.class)`
     * 那我们可以通过反射获取到 InnerTestClass 的构造方法，然后生成一个对象进行测试
     */
    @Test
    public void testInnerClass() throws Exception {
        Class<Object> clazz = Whitebox.getInnerClassType(TestClass.class, "InnerTestClass");
        Constructor<Object> constructor = Whitebox.getConstructor(clazz, String.class);
        // 为 constructor 指定一个 String 类型的参数
        Object instance = constructor.newInstance("TestName");
        try {
            Whitebox.invokeMethod(instance, "run");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }


}
