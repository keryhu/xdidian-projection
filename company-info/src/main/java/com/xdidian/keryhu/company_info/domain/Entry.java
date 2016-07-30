package com.xdidian.keryhu.company_info.domain;

import java.util.ArrayList;
import java.util.List;

public class Entry {

  private String name;

  public Entry(String name) {
     this.name = name;
  }

  private List<Entry> children;

  public void add(Entry node){
     if (children == null)
        children = new ArrayList<Entry>();
     children.add(node);
  }
}
