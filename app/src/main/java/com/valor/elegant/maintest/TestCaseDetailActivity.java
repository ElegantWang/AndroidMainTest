package com.valor.elegant.maintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.valor.elegant.maintest.cases.fragment.TestCaseBaseFragment;
import com.valor.elegant.maintest.cases.source.TestCaseContent;
import com.valor.elegant.maintest.cases.source.TestCaseItem;

/**
 * An activity representing a single TestCase detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link TestCaseListActivity}.
 */
public class TestCaseDetailActivity extends AppCompatActivity {
    public static final String KEY_TEST_CASE_ITEM = "key_test_case_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcase_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        /*if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(TestCaseDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(TestCaseDetailFragment.ARG_ITEM_ID));
            TestCaseDetailFragment fragment = new TestCaseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.testcase_detail_container, fragment)
                    .commit();
        }*/
        TestCaseItem item = getIntent().getParcelableExtra(KEY_TEST_CASE_ITEM);
        TestCaseBaseFragment fragment = TestCaseContent.newTestCaseFragment(item);
        if (fragment == null) {
            Toast.makeText(this, "can't get instance for class:" + item.detail, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setTitle(item.getName());
        getSupportFragmentManager().beginTransaction().add(R.id.testcase_detail_container, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, TestCaseListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Activity activity, TestCaseItem item) {
        Intent intent = new Intent(activity, TestCaseDetailActivity.class);
        intent.putExtra(KEY_TEST_CASE_ITEM, item);
        activity.startActivity(intent);
    }
}
