package com.smartbinapp.smartbin;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Priti_Parate on 10/4/2015.
 */
class MyLinkedMap<K, V> extends LinkedHashMap<K, V>
{

    public V getValue(int i)
    {

        Map.Entry<K, V>entry = this.getEntry(i);
        if(entry == null) return null;

        return entry.getValue();
    }

    public Map.Entry<K, V> getEntry(int i)
    {
        // check if negetive index provided
        Set<Entry<K,V>> entries = entrySet();
        int j = 0;

        for(Map.Entry<K, V>entry : entries)
            if(j++ == i)return entry;

        return null;

    }

    public K getKey(int i){
        Map.Entry<K, V>entry = this.getEntry(i);
        if(entry==null)return null;

        return entry.getKey();
    }

}