package com.mf.browserphoto;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private FragmentActivity mActivity;
	
	public ImageAdapter(FragmentActivity activtiy){
		this.mActivity = activtiy;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ThumbUrls.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ThumbUrls[position];
		//return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 31+ ThumbUrls[position].hashCode();
		//return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
            convertView= mActivity.getLayoutInflater().inflate(R.layout.item,  parent, false);
        }

        //final Item item = getItem(position);
		//Log.d("my", "position = " +position);
        ImageView image = (ImageView)convertView.findViewById(R.id.imageView_item);
        Picasso.with(image.getContext()).load(ThumbUrls[position] ).into(image);

        return convertView;
	}
	
	// references to our images
    public static final String[] ThumbUrls = {
    		"http://img2.ph.126.net/iyv1LGoVGZQHknD5fzJwpw==/1633680764829962060.png",
    		"http://img0.ph.126.net/noLt7uyyl_PGLJVoonMotw==/1633680764829962052.png",
    		"http://img0.ph.126.net/uCADvV4Mrs2AqJ-mmNsK_g==/2038723256316782001.png",
    		"http://img0.ph.126.net/slCguVMmfDxJp0WoqPwjZg==/1603844417298775026.png",
    		"http://img1.ph.126.net/z9VF5KvGBaH2KaTE_2L6Rg==/2023805082551257804.png",
    		"http://img2.ph.126.net/hNQ6E9irlo5gRyrx5GBaGA==/2124010174260243650.png",
    		"http://img1.ph.126.net/KEqwBqWJArDguPB4F9p0vw==/2273191911916567144.png",
    		"http://img2.ph.126.net/KFeufrCMCOhB0hoZzHnMBA==/1836342748061733722.png",
    		"http://img0.ph.126.net/ioQYDXvcMiu3hNlQqd9JRw==/2023805082551257815.png",
    		"http://img1.ph.126.net/Ek24PCCw_-GcfDSG1Qw5hw==/1836342748061733709.png",
    		"http://img1.ph.126.net/W_C3rBNbY1L6en6dbX3SOg==/1367968386815474038.png",
    		"http://img0.ph.126.net/_KQaTLcZf7wOi6_3NVtX5g==/2291487785402740428.png",
    		"http://img2.ph.126.net/FMM0vReWwvg8EAOQhjOj5g==/2038723256316781988.png",
    		"http://img2.ph.126.net/1wEWmhIEPLAFsYlk02ohZA==/6631500973281087041.png",
    		"http://img1.ph.126.net/ux6ZCDY0uk3pkLXRAfMJ5A==/6631469087443884007.png",
    		"http://img0.ph.126.net/EgG0oLTOS_DdK1KQ9fGiYA==/6631530660095039556.png",
    		"http://img1.ph.126.net/k5u4v_2_Y8QWRBB8oje5xA==/6631513067908991770.png",
    		"http://img2.ph.126.net/PSxnBkHHv2t90JB1lg1xvg==/6631619720536883948.png",
    		"http://img2.ph.126.net/aiz6U6QRASmoPr9h8VyS4w==/6631752761443849811.png",
    		"http://img0.ph.126.net/Fs0OfcFberACtvYa8AWIkA==/6631793443374076650.png",
    		"http://img2.ph.126.net/iSyushYZfP40l4empevY7A==/6631793443374076655.png"
    };
    
	
	

}
