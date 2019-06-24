package com.xandone.dog.wcapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: xandone
 * created on: 2018/11/26 23:27
 */
public class HeadArticleBean implements Serializable {


    /**
     * total : 2
     * msg : 成功
     * code : 200
     * rows : [{"userId":"1","articelId":"2","imgUrl":"http://192.168.191.1:8060/images/153492641045052.jpg","title":"标题呵呵","articleUrl":"dsfdf","pageViews":12,"upTime":1543248859000},{"userId":"1","articelId":"2","imgUrl":"http://192.168.191.1:8060/images/153492641045052.jpg","title":"标题呵呵","articleUrl":"dsfdf","pageViews":12,"upTime":1543248865000}]
     */

    private int total;
    private String msg;
    private int code;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * userId : 1
         * articelId : 2
         * imgUrl : http://192.168.191.1:8060/images/153492641045052.jpg
         * title : 标题呵呵
         * articleUrl : dsfdf
         * pageViews : 12
         * upTime : 1543248859000
         */

        private String userId;
        private String articelId;
        private String imgUrl;
        private String title;
        private String articleUrl;
        private int pageViews;
        private long upTime;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getArticelId() {
            return articelId;
        }

        public void setArticelId(String articelId) {
            this.articelId = articelId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticleUrl() {
            return articleUrl;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }

        public int getPageViews() {
            return pageViews;
        }

        public void setPageViews(int pageViews) {
            this.pageViews = pageViews;
        }

        public long getUpTime() {
            return upTime;
        }

        public void setUpTime(long upTime) {
            this.upTime = upTime;
        }
    }
}
