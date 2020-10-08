package com.example.investnotes.fragments;

public interface IMainFragment {
    void showScreensaver(String title, boolean full);
    void hideScreensaver();
    void showMenu();
    void hideMenu();
    void setMenuCursor(int index);
}
