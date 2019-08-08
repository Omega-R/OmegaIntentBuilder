package com.omega_r.omegaintentbuilder.models;

import omega.com.annotations.OmegaExtra;

public class Model {

    @OmegaExtra("Var2")
    String url = "https://developer.android.com/studio/images/hero_image_studio.png";;

    public String getUrl() {
        return url;
    }
}
