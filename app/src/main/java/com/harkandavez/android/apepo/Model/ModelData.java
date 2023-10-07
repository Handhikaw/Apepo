
package com.harkandavez.android.apepo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {

    @SerializedName("id_tree")
    @Expose
    private String idTree;

    @SerializedName("name_tree")
    @Expose
    private String name_tree;

    @SerializedName("desc_tree")
    @Expose
    private String desc_tree;

    @SerializedName("image_resourcee")
    @Expose
    private String image;

    @SerializedName("img1")
    @Expose
    private String image1;

    @SerializedName("img2")
    @Expose
    private String image2;

    @SerializedName("img3")
    @Expose
    private String image3;

    @SerializedName("qr_url")
    @Expose
    private String qr_url;

    public static final String id_tree = "ID_TREE";
    public static final String name_tree_mahasiswa = "ID_TREE";
    public static final String jenis_mahasiswa = "ID_TREE";

    public ModelData(String id, String name_tree, String desc_tree, String image, String image1, String image2, String image3, String qr_url ) {
        this.idTree = id;
        this.name_tree = name_tree;
        this.desc_tree = desc_tree;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.qr_url = qr_url;
    }

    /**
     * 
     * @return
     *     The idTree
     */
    public String getidTree() {
        return idTree;
    }

    /**
     * 
     * @param idTree
     *     The id_tree
     */
    public void setidTree(String idTree) {
        this.idTree = idTree;
    }

    /**
     * 
     * @return
     *     The name_tree
     */
    public String getNama() {
        return name_tree;
    }

    /**
     * 
     * @param name_tree
     *     The Nama
     */
    public void setNama(String name_tree) {
        this.name_tree = name_tree;
    }

    /**
     * 
     * @return
     *     The jenis
     */
    public String getDesc_tree() {
        return desc_tree;
    }

    /**
     * 
     * @param desc_tree
     *     The Jenis
     */
    public void setDesc_tree(String desc_tree) {
        this.desc_tree = desc_tree;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }
    public void setImage1(String image) {
        this.image1 = image;
    }

    public String getImage2() {
        return image1;
    }
    public void setImage2(String image) {
        this.image2 = image;
    }

    public String getImage3() {
        return image2;
    }
    public void setImage3(String image) {
        this.image3 = image;
    }


    public String getQr_url() {
        return qr_url;
    }
    public void setQr_url(String qr_url) {
        this.qr_url = qr_url;
    }

}
