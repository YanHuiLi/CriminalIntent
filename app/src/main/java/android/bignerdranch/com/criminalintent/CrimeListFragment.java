package android.bignerdranch.com.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Archer on 2016/3/7.
 */
public class CrimeListFragment extends ListFragment {


    private static final String TAG="CrimeListFragment";

    private ArrayList<Crime>mCrimes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);

        mCrimes=CrimeLab.get(getActivity()).getCrimes();

        //使用crimeAdapter
//        ArrayAdapter<Crime> adapter=new ArrayAdapter<Crime>(getActivity(),android.R.layout.simple_list_item_1,mCrimes);

   CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

//        Crime c= (Crime) getListAdapter().getItem(position);

        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
//       Log.d(TAG,c.getTitle()+"was Clicked");

        //启动另一个activity

        Intent intent=new Intent(getActivity(),CrimeActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(intent);

    }


    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayList<Crime>crimes){
            super(getActivity(),0,crimes);
        }

        //使用适配器拿到记录并填充
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);

            }
            Crime c=getItem(position);

            //标题
            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            //时间
            TextView dateTextView= (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox= (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckbox);

            solvedCheckBox.setChecked(c.isSolved());


            return  convertView;

        }
    }
}