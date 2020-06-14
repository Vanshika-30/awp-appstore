package com.AppStore.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AppStore.domain.AppCategory;
import com.AppStore.domain.Application;
import com.AppStore.utils.Utils;

public class DatabaseInitialize {

    private List<Application> getAppList() {
        List<Application> allApps = new ArrayList<>();
        allApps.add(new Application(1, "Chrome", "Best browser.Eats RAM.", AppCategory.APPS, 100000000, 4.5d, "images/chrome.jpg", 6.9d));
        allApps.add(new Application(2, "Pubg", "Something that people will abandon really quickly after quarantine", AppCategory.GAMES, 100000000, 4.5d, "images/pubg.png", 6.9d));
        allApps.add(new Application(3, "Tinder", "For the optimists and memers", AppCategory.APPS, 100000000, 4.5d, "images/tinder.jpg", 6.9d));
        allApps.add(new Application(4, "Facebook", "Privacy at its finest.LOL", AppCategory.APPS, 100000000, 4.5d, "images/facebook.jpg", 6.9d));
        allApps.add(new Application(5, "AngryBirds", "One game everyone once played. But dont.", AppCategory.GAMES, 100000000, 4.5d, "images/angrybirds.png", 6.9d));
        allApps.add(new Application(6,"Instagram(beta)","For people with very low affinity to Light mode",AppCategory.BETA,100000,3.6d,"images/instagram.jpg",6.9d));
        allApps.add(new Application(7, "Youtube", "Precious commodity once,now is a daily routine thanks to JIO", AppCategory.APPS, 100000000, 5.0d, "images/youtube.jpg", 6.9d));
        allApps.add(new Application(8, "Spotify", "The original Apple Music", AppCategory.APPS, 100000000, 4.5d, "images/spotify.jpg", 6.9d));
        allApps.add(new Application(9, "Mini militia", "For people with phones without Pubg", AppCategory.GAMES, 100000000, 4.5d, "images/mini.png", 6.9d));
        allApps.add(new Application(10, "UNO", "The game where everyone assumes they know the rules", AppCategory.GAMES, 100000000, 4.5d, "images/uno.png", 6.9d));
        allApps.add(new Application(11, "Zoom", "Poster child for online classes", AppCategory.APPS, 100000000, 4.5d, "images/zoom.jpg", 6.9d));
        allApps.add(new Application(12, "TikTok", "Doesn't deserve a review.Bharat mata ki Jay!", AppCategory.APPS, 100000000, 1.2d, "images/tiktok.svg", 6.9d));
        allApps.add(new Application(13, "Subway Surfer", "So your kid leaves you alone", AppCategory.GAMES, 100000000, 4.5d, "images/subway.jpg", 6.9d));
        allApps.add(new Application(14, "Candy Crush Saga", "Even we don't know why its so popular", AppCategory.GAMES, 100000000, 4.5d, "images/candy.jpg", 6.9d));
        allApps.add(new Application(15, "Teen patti", "For late night dad sessions", AppCategory.GAMES, 100000000, 4.5d, "images/teenpatti.png", 6.9d));
        allApps.add(new Application(16, "Chrome(beta)", "Best browser.Eats RAM.And now glitches too", AppCategory.BETA, 100000000, 4.5d, "images/chrome-beta.jpg", 6.9d));
        allApps.add(new Application(17, "SwiftKey(beta)", "Best keyboard.Gboard can suck it", AppCategory.BETA, 100000000, 4.5d, "images/swiftkey.jpg", 6.9d));
        allApps.add(new Application(18, "MXPlayer(beta)", "Because no one is vile enough to use normal video player", AppCategory.BETA, 100000000, 4.5d, "images/mx.jpg", 6.9d));
        allApps.add(new Application(19, "Youtube studio(beta)", "Why are you reading this just use Vanced", AppCategory.BETA, 100, 4.5d, "images/youtubestudio.png", 6.9d));
        allApps.add(new Application(20, "Firefox(beta)", "Just move on man.", AppCategory.BETA, 100000000, 4.5d, "images/firefox.png", 6.9d));
        allApps.add(new Application(21, "Truecaller", "We protect you by taking all your information", AppCategory.APPS, 100000000, 4.5d, "images/truecaller.jpg", 6.9d));
        allApps.add(new Application(22, "Flipkart", "Just come on big billion day sale.", AppCategory.APPS, 100000000, 4.5d, "images/flipkart.png", 6.9d));
        allApps.add(new Application(23, "Clash of Clans", "For the men with the finest of patience(or tonne of cash)", AppCategory.GAMES, 100000000, 4.5d, "images/coc.png", 6.9d));
        allApps.add(new Application(24, "Fifa Football", "Just buy a console", AppCategory.GAMES, 100000000, 4.5d, "images/fifa.jpg", 6.9d));
        allApps.add(new Application(25, "Facebook(beta)", "Whats better than facebook, a buggy facebook", AppCategory.BETA, 100000000, 4.5d, "images/FacebookBeta.png", 6.9d));
        allApps.add(new Application(26, "Nova Launcher(beta)", "Creativity at its finest", AppCategory.BETA, 100000000, 4.5d, "images/nova.jpg", 6.9d));
        allApps.add(new Application(27, "Amazon", "The delivery time is WOW", AppCategory.APPS, 100000000, 4.5d, "images/amazon.jfif", 6.9d));
        allApps.add(new Application(28, "MyJio", "Because you have to", AppCategory.APPS, 100000000, 4.5d, "images/jio.png", 6.9d));
        allApps.add(new Application(29, "HomeScapes", "Those god damn ads!", AppCategory.GAMES, 100000000, 4.5d, "images/homescapes.jfif", 6.9d));
        allApps.add(new Application(30, "Raid Shadow Legends", "Just for the memes", AppCategory.GAMES, 100000000, 4.5d, "images/raid.jfif", 6.9d));
        return allApps;
    }

    public void initializeDatabase() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zenithdb", "root", Utils.SQL_PASSWORD);) {

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS apps;")) {
                prepStm.execute();
            }

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS mysubscriptiondb;")) {
                prepStm.execute();
            }

            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE apps (id int primary key, name varchar(30), description varchar(150), category varchar(30), downloads int,rating double,logo varchar(200),version double);")) {
                prepStm.execute();
            }

            List<Application> itemsList = getAppList();
            for (Application app : itemsList) {
                try (PreparedStatement prepStm = conn.prepareStatement("INSERT INTO apps (id, name, description, category, downloads,rating,logo,version) values (?,?,?,?,?,?,?,?);");) {
                    prepStm.setInt(1, app.getId());
                    prepStm.setString(2, app.getName());
                    prepStm.setString(3, app.getDescription());
                    prepStm.setString(4, app.getCategory().toString());
                    prepStm.setInt(5, app.getNumDownloads());
                    prepStm.setDouble(6, app.getRating());
                    prepStm.setString(7, app.getLogo());
                    prepStm.setDouble(8, app.getVersion());
                    prepStm.execute();
                }
            }

            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS downloads (userName varchar(30), contents TEXT);")) {
                prepStm.execute();
            }
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS customerData (Name varchar(30), Email varchar(30), Telephone varchar(20),Password varchar(25));")) {
                prepStm.execute();
            }
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE mysubscriptiondb (id int,username varchar(30),status varchar(30));")) {
                prepStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}