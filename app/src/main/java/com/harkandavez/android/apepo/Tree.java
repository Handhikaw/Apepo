package com.harkandavez.android.apepo;

/**
 * Created by Handhika on 7/7/2017.
 */
public class Tree {

    int id;
    private String mTreeName;
    private String mTreeDesc;
    private String mImageURL;
    private String mImage1;
    private String mImage2;
    private String mImage3;
    private String mQRUrl;
    private int mImageResourceId;




    /*public Tree(String treeName, String treeDesc) {
        mTreeName = treeName;
        mTreeDesc = treeDesc;
    }

    public Tree(String treeName, String treeDesc, int imageResourceId) {
        mTreeName = treeName;
        mTreeDesc = treeDesc;
        mImageResourceId = imageResourceId;
    }
    public Tree(String treeName, String treeDesc, String imageURL) {
        mTreeName = treeName;
        mTreeDesc = treeDesc;
        mImageURL = imageURL;
    }*/


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTreeName() {
        return mTreeName;
    }
    public void setTreeName(String treeName) {
        this.mTreeName = treeName;
    }

    public String getTreeDesc() {
        return mTreeDesc;
    }
    public void setTreeDesc(String treeDesc) {
        this.mTreeDesc = treeDesc;
    }

    public String getImageURL() { return mImageURL; }
    public void setImageURL(String imageURL) {
        this.mImageURL = imageURL;
    }
    public String getImage1() { return mImage1; }
    public void setImage1(String image1) {
        this.mImage1 = image1;
    }
    public String getImage2() { return mImage2; }
    public void setImage2(String image2) {
        this.mImage2 = image2;
    }
    public String getImage3() { return mImage3; }
    public void setImage3(String image3) {
        this.mImage3 = image3;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
    public void setImageResourceId(int imageResourceId) {
        this.mImageResourceId = imageResourceId;
    }

    public String getQRUrl() {
        return mQRUrl;
    }
    public void setQrUrl(String qrUrl) {
        this.mQRUrl = qrUrl;
    }
}