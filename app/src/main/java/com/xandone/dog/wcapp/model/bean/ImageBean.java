package com.xandone.dog.wcapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: xandone
 * Created on: 2018/5/3 22:10
 */

public class ImageBean implements Serializable {

    /**
     * total : 2
     * rows : [{"userId":"152146249752110","imgId":"152534259138809","upTime":1525342591000,"imgUrl":"http://192.168.117.128/images/152534259109497.jpg","pageViews":"0"},{"userId":"152146249752110","imgId":"152534286014522","upTime":1525342860000,"imgUrl":"http://192.168.117.128/images/152534285986909.jpg","pageViews":"0"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * userId : 152146249752110
         * imgId : 152534259138809
         * upTime : 1525342591000
         * imgUrl : http://192.168.117.128/images/152534259109497.jpg
         * pageViews : 0
         */

        private String userId;
        private String imgId;
        private long upTime;
        private String imgUrl;
        private String pageViews;
        private int sizeType;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getImgId() {
            return imgId;
        }

        public void setImgId(String imgId) {
            this.imgId = imgId;
        }

        public long getUpTime() {
            return upTime;
        }

        public void setUpTime(long upTime) {
            this.upTime = upTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPageViews() {
            return pageViews;
        }

        public void setPageViews(String pageViews) {
            this.pageViews = pageViews;
        }

        public int getSizeType() {
            return sizeType;
        }

        public void setSizeType(int sizeType) {
            this.sizeType = sizeType;
        }
    }
}
