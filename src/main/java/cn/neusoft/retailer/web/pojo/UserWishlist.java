package cn.neusoft.retailer.web.pojo;

import java.io.Serializable;

public class UserWishlist implements Serializable {
    private static final long serialVersionUID = 9082949110642871725L;
    private Integer wishlistId;

    private Integer bvoId;

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getBvoId() {
        return bvoId;
    }

    public void setBvoId(Integer bvoId) {
        this.bvoId = bvoId;
    }
}