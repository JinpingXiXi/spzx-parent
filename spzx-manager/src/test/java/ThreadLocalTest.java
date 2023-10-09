import com.atguigu.spzx.model.entity.system.SysUser;

/**
 * ThreadLocal 线程变量
 * 1.在同一个线程中，ThreadLocal是共享的
 * 2.不同的线程，ThreadLocal无法共享
 */
public class ThreadLocalTest {

    public static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("张三");
        sysUser.setPassword("123");

        //保存对象到线程变量中
        threadLocal.set(sysUser);

        //同一个线程，ThreadLocal数据是共享的
        show();

        //对另一个线程是隔离的
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SysUser sysUser2 = threadLocal.get();
                System.out.println("子线程:" + sysUser2); //null
            }
        });
        thread.start();
    }

    private static void show() {
        //获取线程变量中的对象
        SysUser sysUser = threadLocal.get();
        System.out.println("show方法：" + sysUser); //有值

        //删除线程变量中的对象
        threadLocal.remove();
        System.out.println("show方法-删除后：" + threadLocal.get()); //null
    }
}
