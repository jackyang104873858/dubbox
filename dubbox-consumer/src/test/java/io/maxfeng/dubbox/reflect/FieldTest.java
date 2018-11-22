package io.maxfeng.dubbox.reflect;

import io.maxfeng.dubbox.biz.D;
import io.maxfeng.dubbox.biz.DubboxDependOnInterface;
import io.maxfeng.dubbox.biz.DubboxInterface;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class FieldTest {

    @Test
    public void testPrivateField() throws IllegalAccessException {
        Field[] fields = DubboxDependOnInterface.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            DubboxDependOnInterface depend = new DubboxDependOnInterface();
            field.set(depend, new D());
            System.out.println(field.get(depend) instanceof DubboxInterface);
        }
    }

    @Test
    public void testClassSimpleName(){
        System.out.println(DubboxInterface.class.getSimpleName());
    }
}
