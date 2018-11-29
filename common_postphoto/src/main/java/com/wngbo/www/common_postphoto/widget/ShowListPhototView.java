package com.wngbo.www.common_postphoto.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wngbo.www.common_postphoto.ISNav;
import com.wngbo.www.common_postphoto.R;
import com.wngbo.www.common_postphoto.config.ISLookConfig;

import java.util.ArrayList;
import java.util.List;

public class ShowListPhototView extends LinearLayout {
    private GridLayout gridlayoutPost;
    private Context mContext;
    private SquareImage sq1,sq2,sq3;
    public ShowListPhototView(Context context) {
        this(context,null);
    }

    public ShowListPhototView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShowListPhototView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }
    private void initView(Context context){
        mContext=context;
        View mView= LayoutInflater.from(mContext).inflate(R.layout.showlistpt_layout,this);
        gridlayoutPost=mView.findViewById(R.id.gridlayout_post);
        gridlayoutPost.setColumnCount(3);
    }
    /**
     * 动态添加控件
     *
     * @param imageModels 图片集合
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateViewGroup(List<String> imageModels) {
        gridlayoutPost.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount=gridlayoutPost.getColumnCount();//得到列数
        Log.e("columnCount",columnCount+"-------");
        int marginSize = dip2px(mContext, 4);//得到经过dp转化的margin值
        //遍历集合 动态添加
        for (int i = 0, size = imageModels.size(); i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = GridLayout.spec(i % columnCount, 1.0f);//列数 列宽的比例 weight=1
            final ImageView imageView = new SquareImage(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mContext).load(imageModels.get(i)).into(imageView);
            imageView.setTag(imageModels.get(i));
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec=rowSpec;
            layoutParams.columnSpec=columnSpec;
            layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
            gridlayoutPost.addView(imageView, layoutParams);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> strings=new ArrayList<>();
                    strings.add((String) imageView.getTag());
                    ISLookConfig config=new ISLookConfig.Builder()
                            .setStartNum(0)
                            .setPhotoPaths(strings)
                            .setLock(false)
                            .build();
                    ISNav.getInstance().toLookPhotoActivity(mContext,config);
                }
            });
        }
    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
