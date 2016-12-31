package com.valor.elegant.maintest.cases.source;

import com.valor.elegant.maintest.cases.fragment.AllContentProviders;
import com.valor.elegant.maintest.cases.fragment.TestCaseBaseFragment;
import com.valor.elegant.maintest.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elegant.wang on 2016/12/26.
 */

public class TestCaseContent {
    public static final List<TestCaseItem> ITEMS = new ArrayList<>();
    public static final Map<String, TestCaseItem> ITEM_MAP = new HashMap<>();
    public static final Map<String, TestCaseBaseFragment> ITEM_FRAGMENTS = new HashMap<>();

    static {
        TestCaseItem allProviders = new TestCaseItem("1", "Get All Providers", AllContentProviders.class.getName());
        addItem(allProviders);
    }

    private static void addItem(TestCaseItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static TestCaseBaseFragment newTestCaseFragment(TestCaseItem testCaseItem) {
        if (ITEM_FRAGMENTS.containsKey(testCaseItem.id)) {
            return ITEM_FRAGMENTS.get(testCaseItem.id);
        }
        try {
            Class clazz = Class.forName(testCaseItem.detail);
            Object instance = clazz.newInstance();
            if (!(instance instanceof TestCaseBaseFragment)) {
                return null;
            }
            ITEM_FRAGMENTS.put(testCaseItem.id, (TestCaseBaseFragment) instance);
            return (TestCaseBaseFragment) instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
