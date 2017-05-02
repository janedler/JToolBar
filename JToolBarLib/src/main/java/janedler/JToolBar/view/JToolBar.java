package janedler.JToolBar.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.ArrayList;

import janedler.JToolBar.R;
import janedler.JToolBar.action.MoreMenuAction;
import janedler.JToolBar.action.base.ContentAction;
import janedler.JToolBar.action.base.MenuAction;
import janedler.JToolBar.listener.MenuActionListener;
import janedler.JToolBar.listener.MenuPopwpWindowDismissActionListener;

/**
 * 财富派APP 通用ToolBar
 *
 * 使用方法：
 *   1.在XML中添加
 *      <janedler.JToolBar.view.JToolBar
 *          android:id="@+id/jToolBar"
 *          android:layout_width="match_parent"
 *          android:layout_height="54dp" />
 *   2.在Activity或者Fragment中进行使用
 *      JToolBar mJToolBar = (JToolBar) findViewById(R.id.jToolBar);
 *    (1).添加标题正文部分
 *      BackAndTitleContentAction backAndTitleContentAction = (BackAndTitleContentAction) mJToolBar.setContentAction(new BackAndTitleContentAction(this));
 *      backAndTitleContentAction.setNavigationIcon(R.drawable.icon_back);
 *      backAndTitleContentAction.setTitle("Hello World");
 *      backAndTitleContentAction.setNavigationOnClickListener(new View.OnClickListener() {
 *          @Override
 *          public void onClick(View v) {
 *              Toast.makeText(MainActivity.this,"Back",Toast.LENGTH_SHORT).show();
 *          }
 *      });
 *     (2).添加MenuAction
 *      RedDotMenuAction redDotMenuActionA = (RedDotMenuAction) mJToolBar.addMenuAction(new RedDotMenuAction(this,RedDotMenuAction.RedDotType.Number,R.drawable.icon_user,"小红点A"));
 *      redDotMenuActionA.updateRedDotNumber(10);
 *      redDotMenuActionA.setOnMenuActionLinstener(new MenuActionListener(){
 *          @Override
 *          public void onClick(View v) {
 *              super.onClick(v);
 *              Toast.makeText(MainActivity.this,"A",Toast.LENGTH_SHORT).show();
 *          }
 *      });
 * PS:标题正文部分 默认提供了BackAndTitleContentAction 如果需要自定义，需要实现ContentAction接口
 *    Menu部分 现已提供了IconMenuAction RedDotMeunAction 如果需要自动以，需要实现MenuAction接口
 *
 * Created by janedler.
 */

public class JToolBar extends FrameLayout {

    //保存Menu
    private ArrayList<MenuAction> menuActions = new ArrayList<>();
    //展示Menu的个数
    private int mShowMenuCount = 2;
    private Context mContext;
    private View mRootView;
    private FrameLayout mContentLayout;
    private LinearLayout mMenuContentLayout;

    public JToolBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public JToolBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(@NonNull Context context){
        this.mContext = context;
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.layout_toolbar_view,null);
        mContentLayout = (FrameLayout) mRootView.findViewById(R.id.content_layout);
        mMenuContentLayout = (LinearLayout) mRootView.findViewById(R.id.menu_layout);
        setTitleBackgroundResource(android.R.color.white);
        this.addView(mRootView);
    }

    /**
     * 设置标题栏背景
     *  如果标题栏默认不设置背景资源 默认为白色
     * @param resid
     */
    public void setTitleBackgroundResource(int resid){
        mRootView.setBackgroundResource(resid);
    }

    /**
     * 设置标题栏正文Action
     *  如果不进行配置 默认为BackCenterTitleContentAction
     * @param contentAction
     */
    public ContentAction setContentAction(ContentAction contentAction){
        mContentLayout.removeAllViews();
        mContentLayout.addView(contentAction.inflateContentView());
        return contentAction;
    }



    /**
     * 添加MenuAction
     * @param menuAction
     */
    public MenuAction addMenuAction(MenuAction menuAction){
        if (menuActions == null) menuActions = new ArrayList<>();
        menuActions.add(menuAction);
        updateMenuAction();
        return menuAction;
    }

    /**
     * 更新MenuAction
     */
    public void updateMenuAction(){

        if(mMenuContentLayout == null) mMenuContentLayout = (LinearLayout) mRootView.findViewById(R.id.menu_layout);
        mMenuContentLayout.removeAllViews();

        for (int i = 0; i < menuActions.size(); i++) {
            MenuAction action = menuActions.get(i);
            if (i+1 < mShowMenuCount){
                View view = action.inflateMenuView();
                if (view == null) continue;
                mMenuContentLayout.addView(view);
            }else{
                View view = buildMoreMenuAction();
                if (view == null) continue;
                mMenuContentLayout.addView(view);
                break;
            }
        }

    }

    /**
     * 更新MenuAction
     */
    public synchronized void updateMenuAction(MenuAction action,int index){
        if(mMenuContentLayout == null) mMenuContentLayout = (LinearLayout) mRootView.findViewById(R.id.menu_layout);
        if (menuActions == null || menuActions.size() <= index) return;
        menuActions.remove(index);
        menuActions.add(index,action);
    }

    /**
     * 创建MoreMenu
     * @return
     */
    private View buildMoreMenuAction(){
        MoreMenuAction action = new MoreMenuAction(mContext,R.drawable.icon_more);
        View view = action.inflateMenuView();
        action.setOnMenuActionLinstener(new MenuActionListener(){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                openMoreWindow();
            }
        });
        return view;
    }

    /**
     * 展开更多
     */
    private void openMoreWindow() {
        if (menuActions == null || menuActions.size() < mShowMenuCount) return;
        final PopupWindow popupWindow = new PopupWindow(mContext, null, 0);
        final LinearLayout root = new LinearLayout(mContext);
        LinearLayout.LayoutParams rootLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setGravity(Gravity.LEFT);
        root.setLayoutParams(rootLP);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#ffffff"));
        root.removeAllViews();
        for (int i = mShowMenuCount-1; i < menuActions.size(); i++) {
            final MenuAction action = menuActions.get(i);
            View view = action.inflateUnfoldMenuView();
            action.setOnMenuPopwpWindowDismissActionListener(new MenuPopwpWindowDismissActionListener() {
                @Override
                public void onPopupWindowDismiss() {
                    popupWindow.dismiss();
                }
            });
            root.addView(view);
        }

        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(root);
        popupWindow.showAsDropDown(mMenuContentLayout,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                root.removeAllViews();
            }
        });

    }

}
