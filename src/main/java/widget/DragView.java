package widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by 张佳亮 on 2016/2/23.
 */
public class DragView extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private View contentview,menuview;
    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 800.0;
    private int draggedX;

    public DragView(Context context) {
        super(context);
        initView();
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView(){
        viewDragHelper=ViewDragHelper.create(this,callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentview=getChildAt(0);
        menuview=getChildAt(1);
        menuview.setVisibility(View.GONE);
    }

    private ViewDragHelper.Callback callback=new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentview || child == menuview;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentview) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound, left), 0);
                return newLeft;
            } else {
                final int minLeftBound = getPaddingLeft() + contentview.getMeasuredWidth() - dragDistance;
                final int maxLeftBound = getPaddingLeft() + contentview.getMeasuredWidth() + getPaddingRight();
                final int newLeft = Math.min(Math.max(left, minLeftBound), maxLeftBound);
                return newLeft;
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);


            super.onViewReleased(releasedChild, xvel, yvel);
            boolean settleToOpen = false;
            if (xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            } else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            } else if (draggedX <= -dragDistance / 2) {
                settleToOpen = true;
            } else if (draggedX > -dragDistance / 2) {
                settleToOpen = false;
            }

            final int settleDestX = settleToOpen ? -dragDistance : 0;
            viewDragHelper.smoothSlideViewTo(contentview, settleDestX, 0);
            ViewCompat.postInvalidateOnAnimation(DragView.this);


    }



        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            draggedX = left;
            if (changedView == contentview) {
                contentview.offsetLeftAndRight(dx);
            } else {
                contentview.offsetLeftAndRight(dx);
            }
            if (menuview.getVisibility() == View.GONE) {
                menuview.setVisibility(View.VISIBLE);
            }
            invalidate();
        }



        @Override
        public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }


    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        dragDistance = menuview.getMeasuredWidth();
    }


    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
