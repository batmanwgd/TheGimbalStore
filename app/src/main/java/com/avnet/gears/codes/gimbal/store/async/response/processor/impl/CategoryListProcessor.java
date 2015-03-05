package com.avnet.gears.codes.gimbal.store.async.response.processor.impl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import com.avnet.gears.codes.gimbal.store.R;
import com.avnet.gears.codes.gimbal.store.async.response.processor.AsyncResponseProcessor;
import com.avnet.gears.codes.gimbal.store.bean.CategoryBean;
import com.avnet.gears.codes.gimbal.store.bean.CategoryResponseBean;
import com.avnet.gears.codes.gimbal.store.constant.GimbalStoreConstants;
import com.avnet.gears.codes.gimbal.store.constant.GimbalStoreConstants.HTTP_RESPONSE_CODES;
import com.avnet.gears.codes.gimbal.store.fragment.NavigationDrawerFragment;
import com.avnet.gears.codes.gimbal.store.utils.TypeConversionUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 914889 on 2/25/15.
 */
public class CategoryListProcessor implements AsyncResponseProcessor {

   private Activity parentActivity;
   private  NavigationDrawerFragment navigationDrawerFragment;
   private List<CategoryBean> categoryBeanList;
    private DrawerLayout drawerLayout;
   private ProgressDialog progressDialog;

    public CategoryListProcessor(Activity parentActivity, NavigationDrawerFragment navigationDrawerFragment,
                                 DrawerLayout drawerLayout,
                                 List<CategoryBean> categoryBeanList, ProgressDialog progressDialog) {
        // add parameters and initialize proper values for
        // sending back the response to the calling activity
        this.categoryBeanList = categoryBeanList;
        this.drawerLayout = drawerLayout;
        this.parentActivity = parentActivity;
        this.navigationDrawerFragment = navigationDrawerFragment;
        this.progressDialog = progressDialog;

    }
    @Override
    public boolean doProcess(HTTP_RESPONSE_CODES responseCode, String responseString){
        // Log.d("PROCESS DEBUG", "" + responseCode);
        responseString = responseString.trim()
                .replace(GimbalStoreConstants.START_COMMENT_STRING, "")
                .replace(GimbalStoreConstants.END_COMMENT_STRING, "");
        if(responseCode == HTTP_RESPONSE_CODES.OK ||
                responseCode == HTTP_RESPONSE_CODES.CREATED ||
                responseCode == HTTP_RESPONSE_CODES.ACCEPTED) {
            Gson gson = new Gson();

            JsonReader reader = new JsonReader(new StringReader(responseString));
            reader.setLenient(true);
            CategoryResponseBean responseBean = gson.fromJson(reader, CategoryResponseBean.class);
            // Log.d("HTTP DEBUG", " Response Bean = " + responseBean);
            // Set up the drawer.
            categoryBeanList.addAll(Arrays.asList(responseBean.getCatalogGroupView()));
            navigationDrawerFragment.setmCategoryTitles(TypeConversionUtil.getCategoryTitleList(categoryBeanList));
            navigationDrawerFragment.refreshDrawerListView();
            // hide progress bar
            progressDialog.dismiss();
            return true;
        }
        return false;
    }
}