package zs.xmx.permission.bean;

import java.util.List;


/**
 * 用户禁止调的权限用在这个JavaBean里面
 */
public class DenyBean {
    private List<String> denyList;

    public List<String> getDenyList() {
        return denyList;
    }

    public void setDenyList(List<String> denyList) {
        this.denyList = denyList;
    }


}
