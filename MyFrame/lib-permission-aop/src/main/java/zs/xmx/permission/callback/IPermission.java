package zs.xmx.permission.callback;

import java.util.List;

public interface IPermission {
    //同意权限
    void PermissionGranted();

    //取消权限
    void PermissionCanceled();

    //拒绝权限并且选中不再提示
    void PermissionDenied(List<String> denyList);
}
