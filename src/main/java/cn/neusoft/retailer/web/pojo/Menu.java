package cn.neusoft.retailer.web.pojo;

import java.util.Objects;

public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuPrivilege;

    private String menuChildrenItem;

    public String getMenuChildrenItem() {
        return menuChildrenItem;
    }

    public void setMenuChildrenItem(String menuChildrenItem) {
        this.menuChildrenItem = menuChildrenItem;
    }

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

    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuPrivilege) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuPrivilege = menuPrivilege;
    }

    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuPrivilege, String menuChildrenItem) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuPrivilege = menuPrivilege;
        this.menuChildrenItem = menuChildrenItem;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuPrivilege=" + menuPrivilege +
                ", menuChildrenItem='" + menuChildrenItem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(getMenuId(), menu.getMenuId()) &&
                Objects.equals(getMenuName(), menu.getMenuName()) &&
                Objects.equals(getMenuUrl(), menu.getMenuUrl()) &&
                Objects.equals(getMenuPrivilege(), menu.getMenuPrivilege()) &&
                Objects.equals(getMenuChildrenItem(), menu.getMenuChildrenItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuId(), getMenuName(), getMenuUrl(), getMenuPrivilege(), getMenuChildrenItem());
    }
}