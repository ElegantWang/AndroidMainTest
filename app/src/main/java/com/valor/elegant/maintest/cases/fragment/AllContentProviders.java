package com.valor.elegant.maintest.cases.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.valor.elegant.maintest.R;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by elegant.wang on 2016/12/26.
 */

public class AllContentProviders extends TestCaseBaseFragment {
    @BindView(R.id.query)
    Button mQueryBtn;
    @BindView(R.id.result)
    TextView mResult;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_all_content_providers;
    }

    @OnClick(R.id.query)
    void query() {
        StringBuilder sb = new StringBuilder();
        for (PackageInfo pack : mActivity.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers) {
                    sb.append(provider.toString()).append("\n");
                }
            }
        }
        Timber.w(sb.toString());
        mResult.setText(sb.toString());
    }
}
