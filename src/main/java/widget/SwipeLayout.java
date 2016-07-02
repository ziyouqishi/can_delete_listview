package widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by Bruce on 11/24/14.
 */
public class SwipeLayout extends LinearLayout {

    private ViewDragHelper viewDragHelper;
    private View contentView;
    private View actionView;
    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 800.0;
    private int draggedX;
    private int screenWidth;
    private Context context;

    public SwipeLayout(Context context) {
        this(context, null);


    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();

    }

    @Override
    protected void onFinishInflate() {
        contentView = getChildAt(0);
        actionView = getChildAt(1);
    }

    void init(){
        viewDragHelper = ViewDragHelper.create(this, new DragHelperCallback());
        /**
         * 获取屏幕宽度
         */
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
         screenWidth=outMetrics.widthPixels;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        dragDistance = actionView.getMeasuredWidth();
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View view, int i) {
            return view == contentView || view == actionView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            /**
             * dx,dy描述的是速度
             */
            super.onViewPositionChanged( changedView,  left,  top,  dx,  dy);
            draggedX = left;
            if (changedView == contentView) {
                actionView.offsetLeftAndRight(dx);
            } else {
                contentView.offsetLeftAndRight(dx);
            }
            invalidate();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound, left), 0);//使其不能向左滑动
                return newLeft;
            } else {
                final int minLeftBound = getPaddingLeft() + contentView.getMeasuredWidth() - dragDistance;
                final int maxLeftBound = getPaddingLeft() + contentView.getMeasuredWidth() + getPaddingRight();
                final int newLeft = Math.min(Math.max(left, minLeftBound), maxLeftBound);
                return newLeft;
            }
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 0;
        }

       /* @Override
        public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }*/

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            /**
             * xvel，yvel表示速度
             */
            super.onViewReleased(releasedChild, xvel, yvel);

            Log.i("liang","左边距离是"+contentView.getLeft());
            Log.i("liang","右边距离是"+contentView.getRight());
            Log.i("liang", "dragDistance是" +dragDistance );
            Log.i("liang", "屏幕宽度是" +screenWidth );


            if(contentView.getRight()<(screenWidth-80)){
                viewDragHelper.smoothSlideViewTo(contentView,  -dragDistance, 0);
                ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);

            }else{

                viewDragHelper.smoothSlideViewTo(contentView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(viewDragHelper.shouldInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
