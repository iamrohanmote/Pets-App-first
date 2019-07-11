package com.example.android.pets;

import android.provider.BaseColumns;

public final class PetsContract {

    private PetsContract() {}

    public static final class petsEntry implements BaseColumns {

        public final static String TABLE_NAME = "Pets";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME ="Name";

        public final static String COLUMN_BREED = "Breed";

        public final static String COLUMN_GENDER = "Gender";

        public final static String COLUMN_WEIGHT = "Weight";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

}