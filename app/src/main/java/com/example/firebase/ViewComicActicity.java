package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.Model.Chapter;
import com.example.firebase.Model.Common;

public class ViewComicActicity extends AppCompatActivity {

    ViewPager viewPager;
    TextView txt_chapter_name;
    View back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic_acticity);

        viewPager =(ViewPager) findViewById(R.id.view_paper);
        txt_chapter_name = (TextView) findViewById(R.id.txt_chapter_name);
        back = findViewById(R.id.chapter_back);
        next = findViewById(R.id.chapter_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex ==0)
                    Toast.makeText(ViewComicActicity.this," you are reading first chapter", Toast.LENGTH_SHORT).show();
                else
                {
                    Common.chapterIndex--;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex ==Common.chapterList.size()-1)
                    Toast.makeText(ViewComicActicity.this," you are reading last chapter", Toast.LENGTH_SHORT).show();
                else
                {
                    Common.chapterIndex++;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
            }
        });
        fetchLinks(Common.chapterSelected);
    }

    private void fetchLinks(Chapter chapter) {
        if(chapter.Links != null){
            if(chapter.Links.size()>0){
                MyViewPaperAdapter adapter = new MyViewPaperAdapter(getBaseContext(), chapter.Links);

            }
            else{
                Toast.makeText(this, "No image here", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "This chapter is translating...", Toast.LENGTH_SHORT).show();
        }
    }
}