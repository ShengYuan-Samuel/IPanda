package com.jiyun.ipanda.model.entity.pandaliveentity;

import java.util.List;

public class PandaLiveBeans {

    private List<TablistBean> tablist;

    public List<TablistBean> getTablist() {
        return tablist;
    }

    public void setTablist(List<TablistBean> tablist) {
        this.tablist = tablist;
    }

    public static class TablistBean {
        /**
         * title : 直播
         * url : http://www.ipanda.com/kehuduan/PAGE14501769230331752/index.json
         * id : TITE1513216453221691
         * order : 1
         */

        private String title;
        private String url;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
