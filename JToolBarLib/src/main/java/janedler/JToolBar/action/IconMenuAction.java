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
 * MenuAction 只有一个图标
 * Created by janedler on 2017/4/28.
 */

public class IconMenuAction implements MenuAction, OnClickListener {

    private Context mContext;
    private int mResId;
    private String mMessage;
    private View mRootView;
    private MenuActionListener mMenuActionListener;
    private MenuPopwpWindowDismissActionListener mMenuPopwpWindowDismissActionListener;
    private ImageView mIconImg;
    private TextView mMessageTv;


    public IconMenuAction(Context context,int resId,String message){
        this.mContext = context;
        this.mResId = resId;
        this.mMessage = message;
        initView();
    }

    private void initView(){
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.title_item_menu_icon_layout,null);
        mIconImg = (ImageView) mRootView.findViewById(R.id.icon);
        mMessageTv = (TextView) mRootView.findViewById(R.id.message);
        mIconImg.setImageResource(mResId);
        mMessageTv.setText(mMessage);
        mIconImg.setVisibility(View.GONE);
        mMessageTv.setVisibility(View.GONE);
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
}
