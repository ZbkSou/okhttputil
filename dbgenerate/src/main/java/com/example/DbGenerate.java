package com.example;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DbGenerate {
    public static void main(String args[]){
        Schema schema = new Schema(1,"okhttp");
        Entity entity =  schema.addEntity("DownloadEntity");

        entity.addLongProperty("start_position");

    }
}
