package com.example.firebase.Interface;

import com.example.firebase.Model.Comic;

import java.util.List;

public interface IComicLoadDone {
    void onComicLoadListener(List<Comic> banner);
}
