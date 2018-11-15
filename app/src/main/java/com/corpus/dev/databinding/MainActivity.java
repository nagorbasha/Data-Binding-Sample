package com.corpus.dev.databinding;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.corpus.dev.databinding.databinding.ActivityHomeBinding;
import com.corpus.dev.databinding.databinding.ChannelItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private ChannelsAdapter channelsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        recyclerView = homeBinding.getRoot().findViewById(R.id.recycler_view);

        homeBinding.setMainActivity(this);

        channelsAdapter = new ChannelsAdapter();

        recyclerView.setAdapter(channelsAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        getData();
    }

    private void getData() {
        try {

            ArrayList<Channels> channelsArrayList = new ArrayList<>();


            for (int k = 0; k < 10; k++) {
                Channels channels1 = new Channels();

                channels1.setName("Channels " + k);

                channels1.setImageUrl("Channel Images " + k);

                channelsArrayList.add(channels1);
            }

            channelsAdapter.setChannelsArrayList(channelsArrayList);

            channelsAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class ChannelsAdapter extends RecyclerView.Adapter<ChannelViewHolder> {


        ArrayList<Channels> channelsArrayList = new ArrayList<>();

        public void setChannelsArrayList(ArrayList<Channels> channelsArrayList) {
            this.channelsArrayList = channelsArrayList;
        }

        @NonNull
        @Override
        public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            ChannelItemBinding channelItemBinding = ChannelItemBinding.inflate(inflater, viewGroup, false);
            return new ChannelViewHolder(channelItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ChannelViewHolder viewHolder, int i) {
            viewHolder.bind(channelsArrayList.get(i));
            viewHolder.channelItemBinding.channelLabel.setText(channelsArrayList.get(i).getName());
        }

        @Override
        public int getItemCount() {
            return channelsArrayList.size();
        }
    }


    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        ChannelItemBinding channelItemBinding;


        ChannelViewHolder(ChannelItemBinding channelItemBinding) {
            super(channelItemBinding.getRoot());
            this.channelItemBinding = channelItemBinding;
        }

        void bind(Channels channel) {
            channelItemBinding.setVariable(BR.obj, channel);
            channelItemBinding.executePendingBindings();
        }

    }

}
