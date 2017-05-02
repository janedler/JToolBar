package janedler.JToolBar.action.base;

import android.view.View;

import janedler.JToolBar.listener.MenuActionListener;
import janedler.JToolBar.listener.MenuPopwpWindowDismissActionListener;

/**
 * MenuAction接口
 *  扩展Menu必须要实现该接口
 *
 * Created by janedler.
 */

public interface MenuAction{

    public View inflateMenuView();

    public View inflateUnfoldMenuView();

    public void setOnMenuActionLinstener(MenuActionListener menuActionListener);

    public void setOnMenuPopwpWindowDismissActionListener(MenuPopwpWindowDismissActionListener menuPopwpWindowDismissActionListener);

}
