package com.wngbo.www.common_postphoto.config;

import java.io.Serializable;
import java.util.List;

public class ISLookConfig implements Serializable{
    public List<String> photoPaths;
    public int startNum;
    public boolean isLock;
    public ISLookConfig(Builder builder){
        this.photoPaths=builder.photoPaths;
        this.startNum=builder.startNum;
        this.isLock=builder.isLock;
    }
    public static class Builder{
        private List<String> photoPaths;
        private int startNum;
        private boolean isLock;
        public Builder setPhotoPaths(List<String> photoPaths) {
            this.photoPaths = photoPaths;
            return this;
        }

        public Builder setLock(boolean lock) {
            isLock = lock;
            return this;
        }

        public Builder setStartNum(int startNum) {
            this.startNum = startNum;
            return this;
        }
        public ISLookConfig build(){
            return new ISLookConfig(this);
        }
    }
}
