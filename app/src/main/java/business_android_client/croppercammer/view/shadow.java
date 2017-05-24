package business_android_client.croppercammer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by seeker on 2017/5/23.
 */

public class shadow extends View {

    private int screenHeitht;
    private int screenWidth;

    public shadow(Context context) {
        this(context,null);
    }

    public shadow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public shadow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(0, 0, screenWidth, screenHeitht);
        canvas.clipRect(getShadowRegionRect(), Region.Op.DIFFERENCE);
        canvas.drawColor(0x60000000);
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenHeitht = getMeasuredHeight();
        screenWidth = getMeasuredWidth();
    }

   private Rect getShadowRegionRect(){
       int height = (int) (screenWidth * 0.8);
       int width = (int) (height * 1.6);
//        int height= (int) (screenWidth/1.6);
       int x_center=screenWidth/2;
       int y_center=screenHeitht/2;
//       return new Rect(0, y_center - (height / 2), screenWidth, height/2 + y_center);
       return new Rect(x_center-(height/2), y_center - (width / 2), x_center+(height/2), (width/2)+ y_center);

    }
}
