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
    private int totallSize;
    private int totallPage;


    public PageBean(int page, int pageSize) {
        super();
        this.pageNow = page;
        this.pageSize = pageSize;
        this.start = (pageNow-1)*pageSize;
    }

    public static Integer getPage_Size() {
        return page_Size;
    }

    public static void setPage_Size(Integer page_Size) {
        PageBean.page_Size = page_Size;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotallSize() {
        return totallSize;
    }

    public void setTotallSize(int totallSize) {
        this.totallSize = totallSize;
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


    public int getTotallPage() {
        return totallPage;
    }

    public void setTotallPage(int totallPage) {
        this.totallPage = totallPage;
    }
}
