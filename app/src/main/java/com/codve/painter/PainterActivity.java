package com.codve.painter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class PainterActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PainterFragment.newInstance();
    }

}
