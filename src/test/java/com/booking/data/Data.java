package com.booking.data;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider
    public static Object[][] bookingParameters() {
        return new Object[][] {
                {"Joe",	    "Juarez",   -10,    true,	"2021-10-01",	"2021-10-15",	"Breakfast",400},
                {"li",	    "juarez",   100,    false,	"2021-10-02",	"2021-10-16",	"all",	    400},
                {"Joe",	    "Jua",	    200,	true,	"2021-10-03",	"2021-10-17",	"meal",	    200},
                {"Juana",	"la",	    300,	false,	"2021-10-04",	"2021-10-18",	"drinks",	400},
                { "",       "perez",	200,	true,"	2021-10-05",	"2021-10-19",	""	,   400},
                {"Juana",   "	"	,   100,	false,	"2021-10-06",	"2021-10-20",		"",  400},
                {"Alicia",	"Juarez",	0,	    true,	"2021-10-07",	"2021-10-21",		"",  400},
                {"Juana",	"mendoza",	1,	    false,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Anahi",	"Cardenaz",	"",     true,	"2021-10-09",	"2021-10-23",	"all",	    400},
                {"Julio",	"Melendrez",50,	    false,	"2021-10-10",	"2021-10-24",	"meal",	    200},
                {"Gabriela","Pancha",  "veinte",false,	"2021-10-11",	"2021-10-25",	"drinks",	400},
                {"Anahi",	"banana",	100,    "",  "2021-10-05",	"2021-10-19",	""	,   400},
                {"julian",	"perez",	100,	"no",	"2021-10-06",	"2021-10-20",	""	,   400},
                {"Anahi",	"banana",	100,	5,	    "2021-10-07",	"2021-10-21",	""	,   400},
                {"julian",	"perez",	100,	true,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-23",	"all",	    200},
                {"julian",	"perez",	100,	0,	    "2021-10-10",	"2021-10-24",	"meal",	    200},
                {"Anahi",	"banana",	100,	1,	    "2021-10-11",	"2021-10-25",	"drinks",	200},
                {"julian",	"perez",	100,	true,	"2020-10-11",	"2021-10-26",	"meal",	    400},
                {"Anahi",	"banana",	100,	false,	"2021-10-11",	"2020-10-11",	"drinks",	400},
                {"julian",	"perez",	100,	true,	"2021-10-10",	"2021-10-09",	"	"	,   400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"	",          " "	,       400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"	"	,	    " ",        400},
                {"Anahi",	"banana",	100,	false,	"hi",	        "2021-10-09",	"Breakfast",400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"hi",	        "all",	    400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-25",	"meal",	    400},
                {"julian",	"perez",	100,	true,	"2021-10-02",	"2021-10-09",	"drinks",	400},
                {"Anahi",	"banana",	100,	false,	"2021-02",	    "2021-10-09",	    "	",  400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"2021-05",	    "	"	,   400},
        };
    }
}
