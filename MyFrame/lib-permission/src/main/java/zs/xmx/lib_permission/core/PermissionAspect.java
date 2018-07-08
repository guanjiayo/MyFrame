package zs.xmx.lib_permission.core;

import android.content.Context;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import zs.xmx.lib_permission.MyPremissionActivity;
import zs.xmx.lib_permission.PermissionUtils;
import zs.xmx.lib_permission.annotation.Permission;
import zs.xmx.lib_permission.annotation.PermissionCanceled;
import zs.xmx.lib_permission.annotation.PermissionDenied;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/25 0:40
 * @本类描述	  处理切入编程AOP的类
 * @内容说明
 *
 */

@Aspect
public class PermissionAspect {

    private static final String TAG = "PermissionAspect";

    //指定需要切入的类名
    @Pointcut("execution(@zs.xmx.lib_permission.annotation.Permission * *(..)) && @annotation(permission)")
    public void requestPermission(Permission permission) {

    }

    /**
     * 处理申请权限前或后要处理的东西
     *
     * @param joinPoint
     * @param permission
     * @throws Throwable
     */
    @Around("requestPermission(permission)")
    public void aroundJointPoint(final ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {

        //初始化context
        Context context = null;

        final Object object = joinPoint.getThis();
        if (joinPoint.getThis() instanceof Context) {
            context = (Context) object;
        } else if (joinPoint.getThis() instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        } else if (joinPoint.getThis() instanceof android.app.Fragment) {
            context = ((android.app.Fragment) object).getActivity();
        } else {
        }

        if (context == null || permission == null) {
            Log.d(TAG, "aroundJonitPoint error ");
            return;
        }

        final Context finalContext = context;
        MyPremissionActivity.requestPermission(context, permission.value(), permission.requestCode(), new IPermission() {
            @Override
            public void ganted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void cancled() {
                PermissionUtils.invokeAnnotation(object, PermissionCanceled.class);
            }

            @Override
            public void denied() {
                PermissionUtils.invokeAnnotation(object, PermissionDenied.class);
                //todo 弹窗提示需要手动设置权限
                PermissionUtils.goToMenu(finalContext);
            }
        });

    }
}
