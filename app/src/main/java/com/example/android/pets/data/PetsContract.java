package com.example.android.pets;

import android.net.Uri;
import android.provider.BaseColumns;

public final class PetsContract {

    private PetsContract() {
    }

    public final static String CONTENT_AUTHORITY = "com.example.android.pets";

    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public final static String PETS_PATH = "Pets";

    public static final class petsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PETS_PATH);

        public final static String TABLE_NAME = "Pets";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME = "Name";

        public final static String COLUMN_BREED = "Breed";

        public final static String COLUMN_GENDER = "Gender";

        public final static String COLUMN_WEIGHT = "Weight";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static boolean isValidGeneder(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE)
                return true;
            else
                return false;
        }
    }

}