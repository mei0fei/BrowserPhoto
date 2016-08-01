package com.mf.browserphoto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class FragmentGrid extends Fragment {
	FragmentViewPager mFragmentViewPager;
	//GridView mGridView;
	ImageAdapter mImageAdapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {//在程序运行期间, 仅被调用一次
		super.onCreate(savedInstanceState);
		Log.d("my", "FragmentGrid onCreate ");
		mImageAdapter = new ImageAdapter(this.getActivity());
		mFragmentViewPager = new FragmentViewPager();
		Bundle bundle = new Bundle();
		mFragmentViewPager.setArguments(bundle);

		FragmentItem frag0 = FragmentItem.create(R.layout.fragment3);

		//一共3个fragment, 循环显示图片
		FragmentItem frag1 = FragmentItem.create(R.layout.fragment1);
		FragmentItem frag2 = FragmentItem.create(R.layout.fragment2);
		FragmentItem frag3 = FragmentItem.create(R.layout.fragment3);

		FragmentItem frag4 = FragmentItem.create(R.layout.fragment1);

		mFragmentViewPager.getFragments().add(frag0);
		mFragmentViewPager.getFragments().add(frag1);
		mFragmentViewPager.getFragments().add(frag2);
		mFragmentViewPager.getFragments().add(frag3);
		mFragmentViewPager.getFragments().add(frag4);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater,container, savedInstanceState);
		Log.d("my", "FragmentGrid onCreateView ");
		View view = inflater.inflate(R.layout.fragment_grid, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d("my", "FragmentGrid onViewCreated ");
		GridView gv = (GridView)view.findViewById(R.id.gridView1);

		gv.setAdapter(mImageAdapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {
				FragmentManager manager= FragmentGrid.this.getActivity()
						.getSupportFragmentManager();

				Bundle bundle = mFragmentViewPager.getArguments();
				bundle.putInt("position", position);
				//mFragmentViewPager.setArguments(bundle);
				Log.d("my", "begin ------");
				Fragment frag = manager.findFragmentByTag("viewpager");
				FragmentTransaction tr = manager.beginTransaction();
				if(frag==null) {
					tr.add(R.id.frag_root, mFragmentViewPager, "viewpager");
				}else{
					tr.show(frag);
					mFragmentViewPager.setCurrentImagePosition(position);
					mFragmentViewPager.refreshImages();
				}
				tr.hide(FragmentGrid.this);
				tr.commit();
				Log.d("my", "end ------");
			}
		});
	}

}
