package fun.server.model.customQuery.TPermissionYZ;

public class TPermissionUserCs {
    //查询传参

    private Long FUserID;//登录的用户ID


    private Long FMenID;//访问的菜单ID

    public Long getFUserID() {
        return FUserID;
    }

    public void setFUserID(Long FUserID) {
        this.FUserID = FUserID;
    }

    public Long getFMenID() {
        return FMenID;
    }

    public void setFMenID(Long FMenID) {
        this.FMenID = FMenID;
    }
}
