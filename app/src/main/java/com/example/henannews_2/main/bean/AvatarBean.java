package com.example.henannews_2.main.bean;

public class AvatarBean {

    /**
     * code : success
     * data : {"width":1080,"height":2060,"filename":"Screenshot_2019-03-17-21-35-41-244_%E5%90%91%E6%97%A5%E8%91%B5%E8%BF%9C%E7%A8%8B%E6%8E%A7%E5%88%B6.png","storename":"5cb69ce2c3b09.png","size":188712,"path":"/2019/04/17/5cb69ce2c3b09.png","hash":"LYdpETkWVbXqcso","timestamp":1555471586,"ip":"2408:84e1:a0:9911:1452:9cc5:2285:3bd6","url":"https://i.loli.net/2019/04/17/5cb69ce2c3b09.png","delete":"https://sm.ms/delete/LYdpETkWVbXqcso"}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * width : 1080
         * height : 2060
         * filename : Screenshot_2019-03-17-21-35-41-244_%E5%90%91%E6%97%A5%E8%91%B5%E8%BF%9C%E7%A8%8B%E6%8E%A7%E5%88%B6.png
         * storename : 5cb69ce2c3b09.png
         * size : 188712
         * path : /2019/04/17/5cb69ce2c3b09.png
         * hash : LYdpETkWVbXqcso
         * timestamp : 1555471586
         * ip : 2408:84e1:a0:9911:1452:9cc5:2285:3bd6
         * url : https://i.loli.net/2019/04/17/5cb69ce2c3b09.png
         * delete : https://sm.ms/delete/LYdpETkWVbXqcso
         */

        private int width;
        private int height;
        private String filename;
        private String storename;
        private int size;
        private String path;
        private String hash;
        private int timestamp;
        private String ip;
        private String url;
        private String delete;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }
    }
}
