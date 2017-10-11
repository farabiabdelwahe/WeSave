package wesave.farabi.com.wesave.Adapters;

/**
 * Created by GSC on 03/10/2017.
 */



    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

    import wesave.farabi.com.wesave.CallDetailsActivityActivity;
    import wesave.farabi.com.wesave.Data.CallForDonation;
    import wesave.farabi.com.wesave.R;

    public class CallForDonationAdapter extends RecyclerView.Adapter<CallForDonationAdapter.MyViewHolder> {

        private Context mContext;
        private List<CallForDonation> calllist;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, count;
            public ImageView thumbnail, overflow;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                count = (TextView) view.findViewById(R.id.count);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                overflow = (ImageView) view.findViewById(R.id.overflow);
            }
        }


        public CallForDonationAdapter(Context mContext, List<CallForDonation> albumList) {
            this.mContext = mContext;
            this.calllist = albumList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.one_call, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
           CallForDonation  callForDonation = calllist.get(position);
            holder.title.setText(callForDonation.getLocation());
            holder.count.setText(callForDonation.getBloodtype() + " Blood type");

            // loading album cover using Glide library
            Log.e("sssssssssssss",callForDonation.getImgUrl());
            Glide.with(mContext).load(callForDonation.getImgUrl()).into(holder.thumbnail);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow ,position);
                }
            });
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view ,int position) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_call, popup.getMenu());
            popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
            popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
  private int position ;
            public MyMenuItemClickListener( int position) {
                this.position=position;
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:

                        Intent i = new Intent(mContext,CallDetailsActivityActivity.class);
                        i.putExtra("call", calllist.get(position));
                        mContext.startActivity(i);
                        return true;
                    case R.id.action_play_next:
                        Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return calllist.size();
        }
    }

