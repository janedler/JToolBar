package janedler.JToolBar.action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import janedler.JToolBar.R;
import janedler.JToolBar.action.base.ContentAction;

/**
 * 默认标题ContentAction.
 *
 * Created by janedler.
 */

public class BackAndTitleContentAction implements ContentAction {

    private Context mContext;
    private TitleStyle mTitleStyle;
    private View mRootView;
    private RelativeLayout mBackLayout;
    private ImageView mBackIconImg;
    private TextView mTitileTv;

    public BackAndTitleContentAction(Context context){
        this(context,TitleStyle.LEFT);
    }

    public BackAndTitleContentAction(Context context,TitleStyle titleStyle){
        this.mContext = context;
        this.mTitleStyle = titleStyle;
    }

    @Override
    public View inflateContentView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.title_item_content_back_title_layout,null);
        mBackLayout = (RelativeLayout) mRootView.findViewById(R.id.back_layout);
        mBackIconImg = (ImageView) mRootView.findViewById(R.id.back_icon);
        mTitileTv = (TextView) mRootView.findViewById(R.id.title_tv);
        if (this.mTitleStyle == TitleStyle.LEFT){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mTitileTv.getLayoutParams();
            params.addRule(RelativeLayout.RIGHT_OF, R.id.back_layout);
        }
        return mRootView;
    }

    public void setNavigationIcon(int resId) {
        mBackIconImg.setImageResource(resId);
    }

    public void setNavigationOnClickListener(View.OnClickListener listener) {
        mBackLayout.setOnClickListener(listener);
    }

    public void setTitle(String resId) {
        mTitileTv.setText(resId);
    }

    public void setTitleTextColor(int color){
        mTitileTv.setTextColor(color);
    }

    public enum TitleStyle{
        LEFT,CENTER
    }
}
