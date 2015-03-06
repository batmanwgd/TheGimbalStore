package com.avnet.gears.codes.gimbal.store.bean;

import java.util.Arrays;

/**
 * Created by 914889 on 3/6/15.
 */
public class ProductsResponseBean {
    private String[] parentCategoryId;
    private String[] catalogId;
    private String[]langId;
    private String[] type;
    private String[] storeId;

    private ProductBean[] CatalogGroupView;

    public String[] getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String[] parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String[] getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String[] catalogId) {
        this.catalogId = catalogId;
    }

    public String[] getLangId() {
        return langId;
    }

    public void setLangId(String[] langId) {
        this.langId = langId;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getStoreId() {
        return storeId;
    }

    public void setStoreId(String[] storeId) {
        this.storeId = storeId;
    }

    public ProductBean[] getCatalogGroupView() {
        return CatalogGroupView;
    }

    public void setCatalogGroupView(ProductBean[] catalogGroupView) {
        CatalogGroupView = catalogGroupView;
    }

    @Override
    public String toString() {
        return "SubCategoryResponseBean{" +
                "parentCategoryId=" + Arrays.toString(parentCategoryId) +
                ", catalogId=" + Arrays.toString(catalogId) +
                ", langId=" + Arrays.toString(langId) +
                ", type=" + Arrays.toString(type) +
                ", storeId=" + Arrays.toString(storeId) +
                ", CatalogGroupView=" + Arrays.toString(CatalogGroupView) +
                '}';
    }
}
