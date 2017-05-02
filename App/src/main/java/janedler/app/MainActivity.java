package janedler.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import janedler.JToolBar.action.BackAndTitleContentAction;
import janedler.JToolBar.action.IconMenuAction;
import janedler.JToolBar.action.RedDotMenuAction;
import janedler.JToolBar.listener.MenuActionListener;
import janedler.JToolBar.view.JToolBar;

public class MainActivity extends AppCompatActivity {

    private JToolBar mJToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJToolBar = (JToolBar) findViewById(R.id.jToolBar);

        RedDotMenuAction redDotMenuActionA = (RedDotMenuAction) mJToolBar.addMenuAction(new RedDotMenuAction(this,RedDotMenuAction.RedDotType.Number,R.drawable.icon_user,"小红点A"));
        redDotMenuActionA.updateRedDotNumber(10);
        redDotMenuActionA.setOnMenuActionLinstener(new MenuActionListener(){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Toast.makeText(MainActivity.this,"A",Toast.LENGTH_SHORT).show();
            }
        });



        IconMenuAction iconMenuAction = (IconMenuAction) mJToolBar.addMenuAction(new IconMenuAction(this,R.drawable.icon_phone,"打电话"));
        iconMenuAction.setOnMenuActionLinstener(new MenuActionListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"B",Toast.LENGTH_SHORT).show();
            }
        });

        RedDotMenuAction redDotMenuActionB = (RedDotMenuAction) mJToolBar.addMenuAction(new RedDotMenuAction(this,RedDotMenuAction.RedDotType.Point,R.drawable.icon_user,"小红点A"));
        redDotMenuActionB.updateRedDotPoint(true);
        redDotMenuActionB.setOnMenuActionLinstener(new MenuActionListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"C",Toast.LENGTH_SHORT).show();
            }
        });


        BackAndTitleContentAction backAndTitleContentAction = (BackAndTitleContentAction) mJToolBar.setContentAction(new BackAndTitleContentAction(this));
        backAndTitleContentAction.setNavigationIcon(R.drawable.icon_back);
        backAndTitleContentAction.setTitle("Hello World");
        backAndTitleContentAction.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Back",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
