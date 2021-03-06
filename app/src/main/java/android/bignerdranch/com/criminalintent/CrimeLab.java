package android.bignerdranch.com.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Archer on 2016/3/7.
 */
public class CrimeLab {

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    private ArrayList<Crime>mCrimes;
    private  static CrimeLab sCrimeLab;
    private Context mAppContext;

    private CrimeLab(Context appContext){
        mAppContext=appContext;
        mCrimes=new ArrayList<Crime>();
        for (int i=0;i<100;i++){
            Crime c=new Crime();
             c.setTitle("Crime#"+i);
            c.setSolved(i%2==0);
            mCrimes.add(c);
        }
    }

    //创建单例
    public static CrimeLab get(Context c){
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

     public Crime getCrime(UUID id){
         for (Crime c: mCrimes){
             if (c.getId().equals(id))
                 return c;
         }
         return null;
     }



}
