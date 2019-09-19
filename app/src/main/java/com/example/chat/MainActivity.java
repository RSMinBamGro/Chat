package com.example.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText editText_inputText;

    private Button button_send;

    private RecyclerView recyclerView_msg;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();

        editText_inputText = (EditText) findViewById(R.id.editText_inputText);
        button_send = (Button) findViewById(R.id.button_send);
        recyclerView_msg = (RecyclerView) findViewById(R.id.recyclerView_msg);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_msg.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList);
        recyclerView_msg.setAdapter(adapter);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String content = editText_inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemChanged(msgList.size() - 1); // 每当有新信息，刷新 RecyclerView 中显示的内容
                    recyclerView_msg.scrollToPosition(msgList.size() - 1); // 将 RecyclerView 定位到最后一行
                    editText_inputText.setText("");
                }
            }
        });
    }

    public void initMsgs () {
        Msg msg = new Msg("hello", Msg.TYPE_RECEIVED);
        msgList.add(msg);

        Msg msg1 = new Msg("hello", Msg.TYPE_SENT);
        msgList.add(msg1);

        msgList.add(msg);
    }
}
