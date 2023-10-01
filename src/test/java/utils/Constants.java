package utils;

public class Constants {
    public static final String CONFIG_READER_PATH =System.getProperty("user.dir")+"/src/test/resources/config/config.properties";

    //I am providing one more / after screenshots so that after / I will provide the path of the ss in such a way all passed ss will be saved in passed folder
    //all failed related ss will be saved in failed folder based on array of bytes
    public static final String SCREENSHOT_FILEPATH=System.getProperty("user.dir")+"/screenshots/";

    //C:\Users\Halim\IdeaProjects\HRMSB16\src\test\resources\testdata
   public static final String EXCEL_READER_PATH=System.getProperty("user.dir")+"/src/test/resources/testdata/batch16TestData.xlsx";

    //public static final String EXCEL_READER_PATH="C:\\Users\\Halim\\IdeaProjects\\HRMSB16\\src\\test\\resources\\testdata\\batch16TestData.xlsx";
}
