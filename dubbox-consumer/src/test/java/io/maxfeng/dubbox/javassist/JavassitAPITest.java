package io.maxfeng.dubbox.javassist;

import javassist.*;
import org.junit.Test;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class JavassitAPITest {

    @Test
    public void testSimpleAPI() {

    }


    interface ByteCode {
        String sayHello();
    }

    public static ByteCode createProxyObject() throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.makeClass(ByteCode.class.getName() + "Proxy");

        cc.addInterface(pool.get(ByteCode.class.getName()));

        CtField name = CtField.make("private String name", cc);

        cc.addField(name);

        return null;
    }
}
