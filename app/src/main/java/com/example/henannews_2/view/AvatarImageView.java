package com.example.henannews_2.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 自定义圆形头像ImageView
 */
public class AvatarImageView extends AppCompatImageView {

    private Paint paint;
    private Bitmap bitmap;
    private Matrix matrix;
    private BitmapShader shader;

    public AvatarImageView(Context context) {
        this(context, null);
    }

    public AvatarImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取ImageView中的Drawable
        notifyViewChanged();
    }

    /**
     * 当图片资源变化时，需调用此方法重新初始化；
     */
    public void notifyViewChanged() {
        BitmapDrawable drawable = (BitmapDrawable) this.getDrawable();
        System.out.println(drawable.getClass().getSimpleName());
        bitmap = drawable.getBitmap();
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setShader(shader);
        matrix = new Matrix();
        this.invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();
        float scaleX = viewWidth * 1.0f / bitmapWidth;
        float scaleY = viewHeight * 1.0f / bitmapHeight;
        float scale = Math.max(scaleX, scaleY);

        matrix.setScale(scale, scale);
        shader.setLocalMatrix(matrix);
        // Bitmap scaledBitmap = Bitmap.createBitmap(bitmap,0,0,bitmapWidth,bitmapHeight,matrix,true);
        // shader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        int radius = Math.min(viewWidth, viewHeight) / 2;
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, radius, paint);
    }
}
