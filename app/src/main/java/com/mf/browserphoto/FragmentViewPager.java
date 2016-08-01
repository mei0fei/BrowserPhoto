package com.mf.browserphoto;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FragmentViewPager extends Fragment {

	FragmentPagerAdapter mAdapter;
	ViewPager mViewPager;
	List<FragmentItem> mFragments = new ArrayList();
	//boolean needRefresh=false;

	public List<FragmentItem> getFragments() {
		return mFragments;
	}

	//public ViewPager getViewPager(){
	//	return mViewPager;
	//}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {//在程序运行期间, 仅被调用一次
		super.onCreate(savedInstanceState);
		Log.d("my", "FragmentViewPager onCreate");

		//if(mAdapter==null){
		mAdapter= new FragmentPagerAdapter(this.getFragmentManager()) {

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFragments.get(arg0);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragments.size();
			}
			/*
			@Override
			public int getItemPosition(Object object) {
				return POSITION_NONE;
			}*/
		};
		//}else{
		//	needRefresh=true;
		//}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("my", "FragmentViewPager onCreateView");
		//读取用户点击图片的下标
		Bundle bundle = this.getArguments();
		int position = bundle.getInt("position");
		Log.d("my", "pos = " + position);

		//设置每个fragment中图片的下标
		mFragments.get(0).setPosition( (position+1)%ImageAdapter.ThumbUrls.length );

		mFragments.get(1).setPosition( (position-1+ImageAdapter.ThumbUrls.length)%ImageAdapter.ThumbUrls.length );
		mFragments.get(2).setPosition(position);
		mFragments.get(3).setPosition( (position+1)%ImageAdapter.ThumbUrls.length );

		mFragments.get(4).setPosition( (position-1+ImageAdapter.ThumbUrls.length)%ImageAdapter.ThumbUrls.length );

		View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d("my", "FragmentViewPager onViewCreated");

		mViewPager  = (ViewPager) view.findViewById(R.id.viewpager1);

		mViewPager.clearOnPageChangeListeners();
		mViewPager.setAdapter(mAdapter);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			int newPos = 1;
			int curPos = 1;

			@Override
			public void onPageSelected(int position) {

				if (curPos == 1 && newPos == 0) {
					mViewPager.setCurrentItem(mFragments.size() - 2, false);

				} else if (curPos == mFragments.size() - 2 && newPos == mFragments.size() - 1) {
					mViewPager.setCurrentItem(1, false);
				}

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {

			}
			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == ViewPager.SCROLL_STATE_DRAGGING) {
					curPos = mViewPager.getCurrentItem();
					Log.d("my", "start dragging " );

				} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
					newPos = mViewPager.getCurrentItem();
					Log.d("my", "state setting ");

				} else if (state == ViewPager.SCROLL_STATE_IDLE) {
					Log.d("my", "state idle ");

					refreshImages();

					Log.d("FragmentItem", "cur frag = " + mViewPager.getCurrentItem());
					Log.d("FragmentItem", "frag 0 image pos = " + mFragments.get(0).getPosition());
					Log.d("FragmentItem", "frag 1 image pos = " + mFragments.get(1).getPosition());
					Log.d("FragmentItem", "frag 2 image pos = " + mFragments.get(2).getPosition());
					Log.d("FragmentItem", "frag 3 image pos = " + mFragments.get(3).getPosition());
					Log.d("FragmentItem", "frag 4 image pos = " + mFragments.get(4).getPosition());


				}
			}
		});

		mViewPager.setCurrentItem(2,false);
/*
		if(needRefresh){//删除在FragmentManager中的fragment缓存, 重新刷新Fragment界面
			FragmentTransaction tr = this.getFragmentManager().beginTransaction();
			tr.remove(mFragments.get(0));
			tr.remove(mFragments.get(1));
			tr.remove(mFragments.get(2));
			tr.remove(mFragments.get(3));
			tr.remove(mFragments.get(4));
			tr.commit();
			//this.getFragmentManager().executePendingTransactions();
			//mAdapter.notifyDataSetChanged();//通知ViewPager刷行界面
			needRefresh=false;
		}
*/
	}

	public void setCurrentImagePosition(int newpos){
		mFragments.get( mViewPager.getCurrentItem()) .setPosition(newpos);
	}

	public void refreshImages(){
		int fragPos = mViewPager.getCurrentItem();//当前fragment的下标
		int picPos = mFragments.get( mViewPager.getCurrentItem()) .getPosition();//当前fragment中图片的下标

		//更新所有fragment中图片的下标
		if(fragPos==2){//根据fragPos的值, 设置前一个fragment中的图片, 和后一个fragment中的图片
			mFragments.get(1).setPosition((picPos-1+ImageAdapter.ThumbUrls.length)%ImageAdapter.ThumbUrls.length);
			mFragments.get(3).setPosition((picPos+1)%ImageAdapter.ThumbUrls.length);

		}else if(fragPos==1){
			mFragments.get(3).setPosition((picPos-1+ImageAdapter.ThumbUrls.length)%ImageAdapter.ThumbUrls.length);
			mFragments.get(2).setPosition((picPos+1)%ImageAdapter.ThumbUrls.length);

		}else if(fragPos==3){
			mFragments.get(2).setPosition((picPos-1+ImageAdapter.ThumbUrls.length)%ImageAdapter.ThumbUrls.length);
			mFragments.get(1).setPosition((picPos+1)%ImageAdapter.ThumbUrls.length);

		}
		mFragments.get(0).setPosition( mFragments.get(3).getPosition() );
		mFragments.get(4).setPosition( mFragments.get(1).getPosition() );

		//根据position值,刷新所有fragments中的图片
		for(int i=0;i<mFragments.size();i++){
			ImageView iv = mFragments.get(i).getImageView();
			if(iv!=null){
				Picasso.with(iv.getContext())
						.load(ImageAdapter.ThumbUrls[ mFragments.get(i).getPosition()  ]).into(iv);

			}
		}
	}

}
