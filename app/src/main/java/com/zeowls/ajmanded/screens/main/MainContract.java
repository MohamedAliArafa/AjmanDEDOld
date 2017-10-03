package com.zeowls.ajmanded.screens.main;

/**
 * Created by root on 10/2/17.
 */

interface MainContract {
    interface ModelView {
        void launchLanding();

        void launchLogin();

        void launchLoginMenu();

        void launchLanguage();

        void requestReadPermission();
    }

    interface UserActions {

    }
}
