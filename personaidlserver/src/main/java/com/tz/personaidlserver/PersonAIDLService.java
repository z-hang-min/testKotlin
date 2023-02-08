package com.tz.personaidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zm on 2023/2/8
 *
 * @Description:
 */
public class PersonAIDLService extends Service {
    ArrayList<Person> persons=new ArrayList<>();

    Binder mBinder=new IPersonManager.Stub(){


        @Override
        public String addPerson(Person person) throws RemoteException {
            Log.e("server","addPerson--"+person.toString());
            person.name="服务端赋值--addPerson";
            return person.toString();
        }

        @Override
        public String outPerson(Person person) throws RemoteException {
            Log.e("server","outPerson--"+person.toString());
            person.name="服务端赋值--outPerson";
            return person.toString();
        }

        @Override
        public String inOutPerson(Person person) throws RemoteException {
            Log.e("server","inOutPerson--"+person.toString());
            person.name="服务端赋值--inOutPerson";
            return person.toString();
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("server","onBInd");

        return mBinder;
    }
}
