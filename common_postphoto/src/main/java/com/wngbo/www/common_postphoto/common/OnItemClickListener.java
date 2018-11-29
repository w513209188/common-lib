package com.wngbo.www.common_postphoto.common;


import com.wngbo.www.common_postphoto.bean.Image;

/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public interface OnItemClickListener {

    int onCheckedClick(int position, Image image);

    void onImageClick(int position, Image image);
}
