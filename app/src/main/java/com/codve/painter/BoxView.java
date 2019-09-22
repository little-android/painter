package com.codve.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxView extends View {
    private static final String TAG = "BoxEvent";
    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    // 从代码创建视图
    public BoxView(Context context) {
        this(context, null);
    }

    // 从布局文件创建视图
    public BoxView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff83f0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "action_down"; // 手指触摸到屏幕
                mCurrentBox = new Box(current);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "action_move"; // 手指在屏幕上移动
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate(); // 重新绘制
                }
                break;
            case MotionEvent.ACTION_UP: // 手指离开屏幕
                action = "action_up";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL: // 父视图拦截了触摸事件
                action = "action_cancel";
                mCurrentBox = null;
                break;
        }
//        Log.i(TAG, action + " at (" + current.x + ", " + current.y + ")");
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);
        Log.i(TAG, mBoxes.size() + "");
        for (Box box : mBoxes) {
            PointF start = box.getStart();
            PointF end  = box.getCurrent();
            float left = Math.min(start.x, end.x);
            float right = Math.max(start.x, end.x);
            float top = Math.min(start.y, end.y);
            float bottom = Math.max(start.y, end.y);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }
}
