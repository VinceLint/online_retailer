package cn.neusoft.retailer.web.tools;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-25 15:08
 */
public class PageBean {

    public static Integer page_Size = 5;
    private int pageNow;
    private int pageSize;
    private int start;


    public PageBean(int page, int pageSize) {
        super();
        this.pageNow = page;
        this.pageSize = pageSize;
        this.start = (pageNow-1)*pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }
    public void setPage(int page) {
        this.pageNow = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (pageNow-1)*pageSize;
    }


}
