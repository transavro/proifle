package tv.cloudwalker.profile;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.OnChildSelectedListener;
import androidx.recyclerview.widget.RecyclerView;

import api.ApiClient;
import api.ApiInterface;
import models.LinkedUsers;
import models.TvEmac;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private static final boolean DEBUG = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfileDialog profileDialog = new ProfileDialog(this);
        profileDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));;
        profileDialog.show();
    }


    public class ProfileDialog extends Dialog {

        private Activity c;
        private HorizontalGridView mHorizontalGridView;

        private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (DEBUG) {
                    final String[] stateNames = {"IDLE", "DRAGGING", "SETTLING"};
                    Log.v(TAG, "onScrollStateChanged "
                            + (newState < stateNames.length ? stateNames[newState] : newState));
                }
            }
        };


        private void createView() {
            mHorizontalGridView = (HorizontalGridView) findViewById(R.id.gridview);
            mHorizontalGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_BOTH_EDGE);
            mHorizontalGridView.setWindowAlignmentOffsetPercent(35);
            mHorizontalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
                @Override
                public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                    if (DEBUG) Log.d(TAG, "onChildSelected position=" + position + " id=" + id);
                }
            });
        }

        private ProfileDialog(Activity a) {
            super(a);
            this.c = a;
        }

        public ProfileDialog(@NonNull Context context) {
            super(context);
        }

        public ProfileDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        protected ProfileDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }


        private View.OnClickListener mItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Clicked !!", Toast.LENGTH_SHORT).show();
                mHorizontalGridView.getAdapter().notifyDataSetChanged();
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);
            createView();
            TvEmac tvEmac = new TvEmac();
            tvEmac.setEmac("ff:ee:dd:cc:bb:aa");
            ApiClient.getClient(MainActivity.this).create(ApiInterface.class).GetLinkedUser(tvEmac).enqueue(new Callback<LinkedUsers>() {
                @Override
                public void onResponse(Call<LinkedUsers> call, Response<LinkedUsers> response) {
                    Log.d(TAG, "onResponse: " + response.code());
                    if (response.code() == 200 && response.body().getUser().size() > 0) {
                        Log.d(TAG, "onResponse: size ===>   " + response.body().getUser().size());
                        mHorizontalGridView.setAdapter(new MyAdapter(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<LinkedUsers> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                }
            });
            mHorizontalGridView.setOnScrollListener(mScrollListener);

            this.setCanceledOnTouchOutside(true);
            this.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    onBackPressed();
                }
            });

            this.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    onBackPressed();
                }
            });
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            c.onBackPressed();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ViewHolder(View v) {
                super(v);
            }
        }

        class MyAdapter extends RecyclerView.Adapter {
            private LinkedUsers linkedUsers;

            MyAdapter(LinkedUsers linkedUsers) {
                this.linkedUsers = linkedUsers;
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (DEBUG) Log.v(TAG, "createViewHolder " + viewType);
                CharacterCardView characterCardView = new CharacterCardView(parent.getContext());
                characterCardView.setOnClickListener(mItemClickListener);
                return new ViewHolder(characterCardView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder baseHolder, int position) {
                if (DEBUG) Log.v(TAG, "bindViewHolder " + position + " " + baseHolder);
                ViewHolder holder = (ViewHolder) baseHolder;
                ((CharacterCardView) holder.itemView).updateUi(linkedUsers.getUser().get(position));
            }

            @Override
            public int getItemCount() {
                return linkedUsers.getUser().size();
            }
        }

    }
}
