package com.tar.investnotes;

import java.util.ArrayList;
import java.util.List;

public class JavaClassTests {

    List<String> list = new ArrayList<>();
    int check = 0;

    void test() {
        check = list.size() > 0 ? 1 : 2;
    }
}
