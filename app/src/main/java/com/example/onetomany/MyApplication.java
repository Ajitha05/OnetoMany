package com.example.onetomany;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

public class MyApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate(){
        super.onCreate();

        //regular SQLite Database
        DaoMaster.DevOpenHelper helper= new DaoMaster.DevOpenHelper(this,"Connectivity",null);
        Database db=helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();

        CustomerDao customerDao = daoSession.getCustomerDao();
        OrderDao orderDao = daoSession.getOrderDao();

    }



    public DaoSession getDaoSession(){
        return daoSession;
    }


}
