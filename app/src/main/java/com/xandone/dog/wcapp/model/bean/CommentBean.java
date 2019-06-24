package com.xandone.dog.wcapp.model.bean;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/16 8:58
 */

public class CommentBean {

    /**
     * total : 2
     * rows : [{"comment_id":"152112642314755","joke_id":"152112021530410","comment_user_id":"152112021530406","comment_details":"写得好","comment_date":1521126423000,"comment_nick":"萝卜","comment_icon":null},{"comment_id":"152112756866240","joke_id":"152112021530410","comment_user_id":"152112021530400","comment_details":"hhha","comment_date":1521127569000,"comment_nick":"二虎","comment_icon":"https://upload-images.jianshu.io/upload_images/2518499-08e4a4b85e9d3aae.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"}]
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

    public static class RowsBean {
        /**
         * comment_id : 152112642314755
         * joke_id : 152112021530410
         * comment_user_id : 152112021530406
         * comment_details : 写得好
         * comment_date : 1521126423000
         * comment_nick : 萝卜
         * comment_icon : null
         */

        private String comment_id;
        private String joke_id;
        private String comment_user_id;
        private String comment_details;
        private long comment_date;
        private String comment_nick;
        private String comment_icon;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getJoke_id() {
            return joke_id;
        }

        public void setJoke_id(String joke_id) {
            this.joke_id = joke_id;
        }

        public String getComment_user_id() {
            return comment_user_id;
        }

        public void setComment_user_id(String comment_user_id) {
            this.comment_user_id = comment_user_id;
        }

        public String getComment_details() {
            return comment_details;
        }

        public void setComment_details(String comment_details) {
            this.comment_details = comment_details;
        }

        public long getComment_date() {
            return comment_date;
        }

        public void setComment_date(long comment_date) {
            this.comment_date = comment_date;
        }

        public String getComment_nick() {
            return comment_nick;
        }

        public void setComment_nick(String comment_nick) {
            this.comment_nick = comment_nick;
        }

        public String getComment_icon() {
            return comment_icon;
        }

        public void setComment_icon(String comment_icon) {
            this.comment_icon = comment_icon;
        }
    }
}
