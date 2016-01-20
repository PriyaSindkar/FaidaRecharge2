package uiReferralSystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import faidarecharge.com.faidarecharge.R;

public class KnowledgeBaseActivity extends AppCompatActivity {
    TextView imgBack;
    private ArrayList<String> parentItems = new ArrayList<String>();

    private ArrayList<Object> childItems = new ArrayList<Object>();
    ExpandableListView exlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_base_open);

        imgBack = (TextView) findViewById(R.id.imgBack);
        imgBack.setText("Knowledge Base");

        exlistview = (ExpandableListView) findViewById(R.id.faq_listView);
        setGroupParents();
        setChildData();
        KnowledgeBaseAdapter adapter = new KnowledgeBaseAdapter(childItems, parentItems);
        adapter.setInflater(
                (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE), this);

        exlistview.setAdapter(adapter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setGroupParents() {
        parentItems.add(getString(R.string.kb_title1));
        parentItems.add(getString(R.string.kb_title2));
        parentItems.add(getString(R.string.kb_title3));
        parentItems.add(getString(R.string.kb_title4));
        parentItems.add(getString(R.string.kb_title5));

    }

    public void setChildData() {
        // Android
        ArrayList<String> child = new ArrayList<String>();

        child.add(getString(R.string.share_content1));
        childItems.add(child);
        childItems.add(child);
        childItems.add(child);
        childItems.add(child);
        childItems.add(child);

        // Core Java
        child = new ArrayList<String>();
        // child.add("Apache");
        // child.add("Applet");
        // child.add("AspectJ");
        // child.add("Beans");
        // child.add("Crypto");
        childItems.add(child);

    }
}
