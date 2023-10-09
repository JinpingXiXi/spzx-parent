import com.atguigu.spzx.model.entity.system.SysUser;

public class AuthContextUtil {
    //INIT THREADLOCAL
    private static final ThreadLocal<SysUser> threadLocal =
            new ThreadLocal<>();
    //SET SYSUSER > THREADLOCAL
    public static void set(SysUser sysUser){
        threadLocal.set(sysUser);
    }
    //
    public static SysUser get(){
        return threadLocal.get();
    }
    //
    public static void remove(){
        threadLocal.remove();
    }
}
