package com.codve.painter;

import android.graphics.PointF;

public class Box {
    private PointF mStart;
    private PointF mCurrent;

    public Box(PointF start) {
        mStart = start;
        mCurrent = start;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getStart() {
        return mStart;
    }
}
