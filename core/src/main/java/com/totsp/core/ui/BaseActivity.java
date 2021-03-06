package com.totsp.core.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.totsp.core.R;

/**
 * @author juancho.
 */
public class BaseActivity extends AppCompatActivity implements BaseFragment.BaseActions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void changeFragment(BaseFragment f) {
        changeFragment(f, false);
    }
    
    protected void changeFragment(BaseFragment f, boolean cleanStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (cleanStack) {
            clearBackStack();
        }
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_enter, R.anim.slide_exit);
        ft.replace(R.id.activity_main, f, f.getClass().getSimpleName());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    /***************************************************************
     * BaseFragment.BaseActions
     */

    @Override
    public void setScreenTitle(int resTitle) {
        setTitle(resTitle);
    }

    @Override
    public void setScreenTitle(String title) {
        setTitle(title);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
