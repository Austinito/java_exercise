package com.vegas.interview;

import com.vegas.interview.core.App;

public class PackageMaker {

    public static void main(String[] args) {
        try {
            App app = new App(args);
            app.run();
        } catch (Exception e) {
            System.err.println("Error running application: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
