package com.tar.investnotes.fragments

interface IMainFragment {
    fun showScreensaver(title: String?, full: Boolean)
    fun hideScreensaver()
    fun showMenu()
    fun hideMenu()
    fun setMenuCursor(index: Int)
}