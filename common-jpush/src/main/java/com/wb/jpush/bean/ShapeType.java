package com.wb.jpush.bean;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@IntDef({
        ShapeType.QQ,
        ShapeType.SINA,
        ShapeType.WX,
})
@Retention(RetentionPolicy.SOURCE)
public @interface ShapeType {
    int QQ=1;
    int WX=2;
    int SINA=3;
}
