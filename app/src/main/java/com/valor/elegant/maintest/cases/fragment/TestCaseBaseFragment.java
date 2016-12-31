package com.valor.elegant.maintest.cases.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valor.elegant.maintest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elegant.wang on 2016/12/26.
 */

public class TestCaseBaseFragment extends Fragment {
    @BindView(R.id.fragment_root)
    ViewGroup mRootView;
    Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = (ViewGroup) inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @LayoutRes
    public int getLayoutResId() {
        return R.layout.fragment_test_case_base;
    }

    public static TestCaseBaseFragment newInstance() {

        Bundle args = new Bundle();

        TestCaseBaseFragment fragment = new TestCaseBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
