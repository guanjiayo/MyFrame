package zs.xmx.permission.aspect;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import zs.xmx.permission.PermissionRequestActivity;
import zs.xmx.permission.annotation.NeedPermission;
import zs.xmx.permission.annotation.PermissionCanceled;
import zs.xmx.permission.annotation.PermissionDenied;
import zs.xmx.permission.bean.DenyBean;
import zs.xmx.permission.callback.IPermission;
import zs.xmx.permission.utils.PermissionUtils;
import zs.xmx.permission.utils.SettingUtils;

/**
 * Aspect 权限切面类
 * <p>
 * 包含@PointCut 和 @Advice
 */
@Aspect
public class PermissonAspect {
    private static final String TAG = "PermissonAspect";

    private static final String PERMISSION_REQUEST_POINTCUT =
            "execution(@zs.xmx.lib_permission.annotation.NeedPermission * *(..))";

    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(needPermission)")
    public void requestPermissionMethod(NeedPermission needPermission) {
    }

    @Around("requestPermissionMethod(needPermission)")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint, final NeedPermission needPermission) throws Throwable{
        Context context = null;
        final Object object = joinPoint.getThis();
        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            context = ((android.app.Fragment) object).getActivity();
        }
        if (context == null || needPermission == null) {
            Log.e(TAG, "aroundJonitPoint error ");
            return;
        }

        PermissionRequestActivity.PermissionRequest(context, needPermission.value(),
                needPermission.requestCode(), new IPermission() {
                    @Override
                    public void PermissionGranted() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void PermissionCanceled() {
                        PermissionUtils.invokeAnnotation(object, PermissionCanceled.class, null, false);
                    }

                    @Override
                    public void PermissionDenied(List<String> denyList) {
                        PermissionUtils.invokeAnnotation(object, PermissionDenied.class, denyList, true);
                    }
                }
        );
    }

}
