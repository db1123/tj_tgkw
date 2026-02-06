package  fun.server.controller.solr.model;

import java.util.List;

/**
 * 搜索结果封装类
 */
public class SearchResult {
    // 文档列表
    private List<Document> list;
    // 总记录数
    private long total;
    // 当前页码
    private int page;

    // 默认构造函数
    public SearchResult() {}

    // 带参数构造函数
    public SearchResult(List<Document> list, long total, int page) {
        this.list = list;
        this.total = total;
        this.page = page;
    }

    // Getter和Setter方法 - 修复的核心：添加必要的setter
    public List<Document> getList() {
        return list;
    }

    public void setList(List<Document> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
    