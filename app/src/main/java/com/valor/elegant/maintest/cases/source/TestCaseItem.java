package com.valor.elegant.maintest.cases.source;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elegant.wang on 2016/12/26.
 */

public class TestCaseItem implements Parcelable {
    public String id;
    public String name;
    public String detail;

    public TestCaseItem() {
    }

    public TestCaseItem(String id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected TestCaseItem(Parcel in) {
        Bundle bundle = in.readBundle();
        this.id = bundle.getString("id");
        this.name = bundle.getString("name");
        this.detail = bundle.getString("detail");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("detail", detail);
        dest.writeBundle(bundle);
    }

    public static final Creator<TestCaseItem> CREATOR = new Creator<TestCaseItem>() {
        @Override
        public TestCaseItem createFromParcel(Parcel source) {
            return new TestCaseItem(source);
        }

        @Override
        public TestCaseItem[] newArray(int size) {
            return new TestCaseItem[size];
        }
    };
}
