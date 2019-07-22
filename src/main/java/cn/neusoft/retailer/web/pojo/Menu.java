package cn.neusoft.retailer.web.pojo;

public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuPrivilege;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getMenuPrivilege() {
        return menuPrivilege;
    }

    public void setMenuPrivilege(Integer menuPrivilege) {
        this.menuPrivilege = menuPrivilege;
    }
}