package com.valor.elegant.maintest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.valor.elegant.maintest.cases.fragment.TestCaseBaseFragment;
import com.valor.elegant.maintest.cases.source.TestCaseContent;
import com.valor.elegant.maintest.cases.source.TestCaseItem;
import com.valor.elegant.maintest.dummy.DummyContent;
import com.valor.elegant.maintest.ui.decoration.DividerItemDecoration;

import java.util.List;

/**
 * An activity representing a list of TestCases. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TestCaseDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class TestCaseListActivity extends AppCompatActivity {
    public static String TAG = TestCaseListActivity.class.getCanonicalName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcase_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.testcase_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.testcase_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.e(TAG, "items size:" + TestCaseContent.ITEMS);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(TestCaseContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<TestCaseItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<TestCaseItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.testcase_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).name);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        /*Bundle arguments = new Bundle();
                        arguments.putString(TestCaseDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        TestCaseDetailFragment fragment = new TestCaseDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.testcase_detail_container, fragment)
                                .commit();*/
                        Fragment fragment = TestCaseContent.newTestCaseFragment(holder.mItem);
                        if (fragment == null) {
                            Toast.makeText(holder.mView.getContext(), "can't get instance for class:" + holder.mItem.detail, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.testcase_detail_container, fragment).commit();
                    } else {
                        /*Context context = v.getContext();
                        Intent intent = new Intent(context, TestCaseDetailActivity.class);
                        intent.putExtra(TestCaseDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);*/
                        TestCaseDetailActivity.start(TestCaseListActivity.this, holder.mItem);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public TestCaseItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
