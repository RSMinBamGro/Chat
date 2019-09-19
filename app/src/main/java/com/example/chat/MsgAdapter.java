package com.example.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout_left;

        LinearLayout layout_right;

        TextView textView_leftMsg;

        TextView textView_rightMsg;

        public ViewHolder (View view) {
            super(view);
            layout_left = (LinearLayout) view.findViewById(R.id.layout_left);
            layout_right = (LinearLayout) view.findViewById(R.id.layout_right);
            textView_leftMsg = (TextView) view.findViewById(R.id.textView_leftMsg);
            textView_rightMsg = (TextView) view.findViewById(R.id.textView_rightMsg);
        }
    }

    public MsgAdapter (List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.layout_left.setVisibility(View.VISIBLE);
            holder.layout_right.setVisibility(View.GONE);
            holder.textView_leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SENT) {
            holder.layout_left.setVisibility(View.GONE);
            holder.layout_right.setVisibility(View.VISIBLE);
            holder.textView_rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount () {
        return mMsgList.size();
    }
}
