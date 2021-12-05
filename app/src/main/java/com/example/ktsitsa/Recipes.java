package com.example.ktsitsa;

public class Recipes {
    private String recipeName;
    private String recipeDescription;
    private String recipeIngredients;
    private String method;
    private String recipeImage;
    private String key;
    private String uid;
    private boolean approved;

    public String getKey() {
        return key;
    }

    public Recipes(){}

    public Recipes(String recipeName,String method, String recipeDescription, String recipeIngredients, String recipeImage, String Key, String uid) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeImage = recipeImage;
        this.method = method;
        this.recipeIngredients = recipeIngredients;
        this.key = Key;
        this.approved = false;
        this.uid = uid;
    }

    public boolean isApproved(){
        return approved;
    }

    public String getUid() {
        return uid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }


}
