package com.AppStore.domain;

import com.google.gson.Gson;

public class UserDownloadsStatus {
    public String username;
    public String contents;

    public UserDownloadsStatus(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }

    public void addApp(Application app) {
        Gson g = new Gson();
        Application a = g.fromJson(app.json(), Application.class);
        
        String s = "<div class='col-lg-4 col-md-6 mb-4'><div class='card h-100'><a href='individualPage.html?id="+String.valueOf(a.getId())+"'><img class='card-img-top' src='"+a.getLogo()+"' alt='"+a.getName()+"'></a><div class='card-body'><h4 class='card-title'><a href='individualPage.html?id="+String.valueOf(a.getId())+"'>"+a.getName()+"</a></h4><h5>Version = "+String.valueOf(a.getVersion())+"</h5><p class='card-text'>"+a.getDescription()+"</p></div><div class='card-footer'><small class='text-muted'>Rating: "+String.valueOf(a.getRating())+"</small></div></div></div>";
        contents = contents + s + "|downloading|0\t";
    }

    public void incrementProgresses() {
        StringBuilder contentsNew = new StringBuilder();
        String[] parts = contents.split("\t");
        for (String part : parts) {
            String[] oneApp = part.split("\\|");
            contentsNew.append(oneApp[0]);
            int curProgress = Integer.parseInt(oneApp[2]);
            String status = oneApp[1];
            String newProg = oneApp[2];
            if (status.equals("downloading")) {
                newProg = String.valueOf(curProgress + 1);
                if (newProg.equals("100")) {
                    status = "done";
                }
            }
            contentsNew.append("|").append(status).append("|").append(newProg).append("\t");
        }
        contents = contentsNew.toString();
    }
}
