package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//jdk动态代理类
public class SubjectImplJDKProxy implements InvocationHandler {

    private Object object; // 要代理的对象

    public SubjectImplJDKProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        // 添加事务控制
        System.out.println("=========jdk动态代理=========");
        System.out.println("在调用前,我要开始事务");
        Object returnValue = method.invoke(this.object, args);
        System.out.println("在调用后,我要提交事务");
        System.out.println("=========jdk动态代理=========");
        return returnValue;
    }

    // 获取代理对象
    public Object getObjectProxy() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), new SubjectImplJDKProxy(object));
    }

}