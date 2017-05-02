package janedler.JToolBar.action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import janedler.JToolBar.R;
import janedler.JToolBar.action.base.MenuAction;
import janedler.JToolBar.listener.MenuActionListener;
import janedler.JToolBar.listener.MenuPopwpWindowDismissActionListener;

/**
 * MenuAction 图标 小红点/数字
 * Created by janedler on 2017/4/28.
 */

public class RedDotMenuAction implements MenuAction, OnClickListener {

    private Context mContext;
    private RedDotType mType;
    private int mResId;
    private String mMessage;
    private View mRootView;
    private MenuActionListener mMenuActionListener;
    private MenuPopwpWindowDismissActionListener mMenuPopwpWindowDismissActionListener;
    private ImageView mIconImg;
    private TextView mMessageTv;
    private TextView mRedDotTv;
    private TextView mRedDotNumTv;


    public RedDotMenuAction(Context context,RedDotType type, int resId, String message){
        this.mContext = context;
        this.mType = type;
        this.mResId = resId;
        this.mMessage = message;
        initView();
    }

    private void initView(){
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.title_item_menu_red_dot_layout,null);
        mIconImg = (ImageView) mRootView.findViewById(R.id.icon);
        mMessageTv = (TextView) mRootView.findViewById(R.id.message);
        mRedDotTv = (TextView) mRootView.findViewById(R.id.reddot);
        mRedDotNumTv = (TextView) mRootView.findViewById(R.id.reddot_number);
        mIconImg.setImageResource(mResId);
        mMessageTv.setText(mMessage);
        mIconImg.setVisibility(View.GONE);
        mMessageTv.setVisibility(View.GONE);
        mRedDotTv.setVisibility(View.GONE);
        mRedDotNumTv.setVisibility(View.GONE);
        mRootView.setOnClickListener(this);
    }

    @Override
    public View inflateMenuView() {
        mIconImg.setVisibility(View.VISIBLE);
        mMessageTv.setVisibility(View.GONE);
        return mRootView;
    }

    @Override
    public View inflateUnfoldMenuView() {
        mIconImg.setVisibility(View.VISIBLE);
        mMessageTv.setVisibility(View.VISIBLE);
        return mRootView;
    }


    public void updateRedDotPoint(boolean isShowPoint){
        if (mType == RedDotType.Point)mRedDotTv.setVisibility(isShowPoint?View.VISIBLE:View.GONE);
    }

    public void updateRedDotNumber(int number){
        if (mType == RedDotType.Number)mRedDotNumTv.setVisibility(number>0?View.VISIBLE:View.GONE);
        if (number >= 99) number = 99;
        mRedDotNumTv.setText(number+"");
    }

    @Override
    public void setOnMenuActionLinstener(MenuActionListener menuActionListener) {
        this.mMenuActionListener = menuActionListener;
    }

    @Override
    public void setOnMenuPopwpWindowDismissActionListener(MenuPopwpWindowDismissActionListener menuPopwpWindowDismissActionListener) {
        this.mMenuPopwpWindowDismissActionListener = menuPopwpWindowDismissActionListener;
    }


    @Override
    public void onClick(View v) {
        if (mMenuActionListener != null){
            mMenuActionListener.onClick(v);
        }
        if (mMenuPopwpWindowDismissActionListener != null){
            mMenuPopwpWindowDismissActionListener.onPopupWindowDismiss();
        }
    }

    public enum RedDotType{
        Point,Number
    }

}
