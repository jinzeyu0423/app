package com.example.version11;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

class DeleteDraw extends Drawable {
    private float mSize;
    private Paint mPaint;

    public DeleteDraw(float size) {
        mSize = size;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        float space = mSize / 5;
        float centerX = mSize / 2;
        float centerY = mSize / 2;
        float width = mSize / 10;

        mPaint.setColor(Color.GRAY);//灰色
        canvas.drawCircle(centerX, centerY, centerX, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.save();
        canvas.rotate(45, centerX, centerY);
        canvas.drawRect(centerX - width / 2, space, centerX + width / 2, mSize - space, mPaint); //画矩形线
        canvas.restore();
        canvas.rotate(135, centerX, centerY);
        canvas.drawRect(centerX - width / 2, space, centerX + width / 2, mSize - space, mPaint); //画矩形线
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

}