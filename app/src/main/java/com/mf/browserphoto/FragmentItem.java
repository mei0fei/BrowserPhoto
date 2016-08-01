package com.mf.browserphoto;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentItem extends Fragment {
	int mlayout=-1;
	ImageView mImageView; //图片控件
	int position=-1;//图片的下标
	
	public static FragmentItem create(int layout){
		FragmentItem item = new FragmentItem();
		item.mlayout = layout;
		return item;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
		
	}

	public ImageView getImageView(){
		return mImageView;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("FragmentItem", "FragmentItem onCreate");
		Log.d("FragmentItem", "fragments size = " +  this.getActivity().getSupportFragmentManager().getFragments().size() );
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater,container,savedInstanceState);
		//Log.d("FragmentItem", "FragmentItem onCreateView " );
		View view = null;
		if(mlayout>-1){
			view = inflater.inflate(mlayout, null);
			mImageView =(ImageView) view.findViewById(R.id.imageView1);
		}
		
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		Log.d("FragmentItem", "tag=" + this.getTag() );
		Log.d("FragmentItem", "FragmentItem onViewCreated position=" +position );
		super.onViewCreated(view, savedInstanceState);
		Picasso.with(mImageView.getContext())
			.load(ImageAdapter.ThumbUrls[position]).into(mImageView);

	}


}
